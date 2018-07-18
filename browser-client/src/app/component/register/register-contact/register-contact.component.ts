import { Router } from "@angular/router";
import { NgForm } from "@angular/forms";
import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { User } from "../../../model/user.model";
import { AuthenticationService } from "../../../service/auth/authentication.service";
import { NotificationsService } from "angular2-notifications";
import { touchForm } from "../../../service/utils";

@Component({
  selector: "app-register-contact",
  templateUrl: "./register-contact.component.html",
  styleUrls: ["../register-logic.css"]
})
export class RegisterContactComponent implements OnInit {
  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private _notifications: NotificationsService
  ) {}

  @Input() public user: User;
  @Output() public prev = new EventEmitter<any>();
  public repeatPassword: string;
  public passwordError = false;

  ngOnInit() {}

  onFormSubmit(form: NgForm) {
    if (this.repeatPassword != this.user.password) {
      this.passwordError = true;
      return;
    } else this.passwordError = false;
    if (form.valid) {
      this.authService
        .signup({
          user: this.user,
          password: this.user.password,
          email: this.user.email
        })
        .subscribe(
          result => {
            this.user = result.user;
            this._notifications.success("Success", result.message);
            this.router.navigate(["/login"]);
          },
          error => {
            console.log(error);
            this._notifications.error(
              "Registration error",
              error.error.message
            );
          }
        );
    } else {
      touchForm()
    }
  }

  goBack() {
    this.prev.next({});
  }
}
