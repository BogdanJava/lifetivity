import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../../service/auth/authentication.service";
import { Router } from "@angular/router";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent {
  public email: string;
  public password: string;
  public loading: boolean = false;

  constructor(private authService: AuthenticationService, private router: Router) {}

  public submitForm() {
    this.loading = true;
    this.authService.signup(this.email, this.password).subscribe(
      result => {
        console.log(result);
        this.loading = false;
        this.router.navigate(['/login'])
      },
      _ => {
        console.log("Incorrect email or password");
        this.loading = false;
      }
    );
  }
}
