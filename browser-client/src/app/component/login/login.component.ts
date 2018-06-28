import { AuthenticationService } from "./../../service/auth/authentication.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent {
  public email: string;
  public password: string;

  constructor(private authService: AuthenticationService) {}

  public submitForm() {
    this.authService.login(this.email, this.password).subscribe(result => {
      console.log(result);
    });
  }
}
