import { UserService } from "./../../service/user/user.service";
import { AuthenticationService } from "./../../service/auth/authentication.service";
import { Component, OnInit, Output, EventEmitter } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "../../model/user.model";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent {
  public email: string;
  public password: string;
  public loading: boolean = false;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private userService: UserService
  ) {}

  public submitForm() {
    this.loading = true;
    this.authService.login(this.email, this.password).subscribe(
      result => {
        localStorage.setItem("token", result.token);
        console.log(result);
        this.loading = false;
        this.router.navigate(["/home"]);
        this.userService.getCurrentUser().subscribe(result => {
          this.userService.setUser(result)
        })
      },
      _ => {
        console.log("Incorrect email or password");
        this.loading = false;
      }
    );
  }
}
