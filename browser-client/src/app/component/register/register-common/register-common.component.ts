import { AuthenticationService } from "./../../../service/auth/authentication.service";
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { User } from "../../../model/user.model";
import { NgForm } from "@angular/forms";
import { when } from "q";
import { touchForm } from "../../../service/utils";

@Component({
  selector: "app-register-common",
  templateUrl: "./register-common.component.html",
  styleUrls: ["../register-logic.css"]
})
export class RegisterCommonComponent implements OnInit {
  constructor(private authService: AuthenticationService) {}

  @Input() public user: User;
  @Output() public next = new EventEmitter<User>();
  public emailExists: boolean = false;
  public loading = false;

  ngOnInit() {}

  onFormSubmit(form: NgForm) {
    this.emailExists = false;
    this.loading = true;
    if (form.valid) {
      this.authService.isEmailReserved(this.user.email).subscribe(
        result => {
          if (!result) {
            this.next.next(this.user);
          } else {
            this.emailExists = true;
          }
          this.loading = false;
        },
        _ => {
          this.loading = false;
        }
      );
    } else {
      this.loading = false;
      touchForm()
    }
  }
}
