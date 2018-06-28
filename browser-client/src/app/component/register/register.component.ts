import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../service/auth/authentication.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent {
  public email: string;
  public password: string;

  constructor(private authService: AuthenticationService) {}

  public submitForm() {
    this.authService.login(this.email, this.password).subscribe(result => {
      console.log(result);
    });
  }
}
