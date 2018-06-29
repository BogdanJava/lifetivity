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
  public loading: boolean = false;

  constructor(private authService: AuthenticationService) {}

  public submitForm() {
    this.loading = true;
    this.authService.login(this.email, this.password).subscribe(
      result => {
        localStorage.setItem('token', result.token)
        console.log(result);
        this.loading = false;
      },
      _ => {
        console.log("Incorrect email or password");
        this.loading = false;
      }
    );
  }
}
