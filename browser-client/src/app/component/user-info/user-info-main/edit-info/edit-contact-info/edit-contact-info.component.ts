import { UserService } from "./../../../../../service/user/user.service";
import { Component, OnInit } from "@angular/core";
import { User } from "../../../../../model/user.model";
import { ContactInfo } from "../../../../../model/contact-info.model";
import { NgForm } from "@angular/forms";

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
  constructor(private userService: UserService) {}

  public contactInfo: ContactInfo;

  ngOnInit(): void {
    this.userService.getUserContactInfo().subscribe(result => {
      this.contactInfo = result
    })
  }

  onFormSubmit(form: NgForm) {
    if(form.valid) {
      this.userService.updateUserContactInfo(this.contactInfo).subscribe(result => {
        console.log(result)
      })
    }
  }
}
