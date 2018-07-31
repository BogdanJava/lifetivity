import { UserService } from "./../../../../../service/user/user.service";
import { Component, OnInit } from "@angular/core";
import { User } from "../../../../../model/user.model";
import { ContactInfo } from "../../../../../model/contact-info.model";
import { NgForm } from "@angular/forms";
import { NotificationsService } from "angular2-notifications";

@Component({
  templateUrl: "./edit-contact-info.component.html",
  styleUrls: [
    "./edit-contact-info.component.css",
    "../edit-info.component.css",
    "../../user-photo/user-photo.component.css"
  ],
  selector: "app-edit-contact-info"
})
export class EditContactInfoComponent implements OnInit {
  constructor(
    private userService: UserService,
    private _notifications: NotificationsService
  ) {}

  public infoUpdated = false;
  public contactInfo: ContactInfo;
  public user: User;

  ngOnInit(): void {
    this.userService.getCurrentUserObservable().subscribe(user => {
      this.user = user;
    })
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
      this.userService.getUserContactInfo(this.user.id).subscribe(result => {
        this.contactInfo = result;
      });
    })
  }

  onFormSubmit(form: NgForm) {
    if (form.valid) {
      this.userService.updateUserContactInfo(this.contactInfo, this.user.id).subscribe(
        result => {
          this.contactInfo = result;
          this.infoUpdated = true;
        },
        error => {
          this._notifications.error("Update error", error.message);
        }
      );
    }
  }
}
