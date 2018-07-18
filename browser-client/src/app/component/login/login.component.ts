import { UserService } from "./../../service/user/user.service";
import { AuthenticationService } from "./../../service/auth/authentication.service";
import {
  Component,
  OnInit,
  ViewChild,
  ElementRef
} from "@angular/core";
import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"]
})
export class LoginComponent implements OnInit {
  public email: string;
  public password: string;
  public loading = false;
  public showAlert = false;
  public alertMessage: string;
  @ViewChild("emailInput") public emailInput: ElementRef;
  @ViewChild("passwordInput") public passwordInput: ElementRef;

  constructor(
    private authService: AuthenticationService,
    private router: Router,
    private userService: UserService
  ) {}

  public submitForm() {
    this.loading = true;
    this.authService.login(this.email, this.password).subscribe(
      result => {
        this.authService.setLoggedIn(true);
        localStorage.setItem("token", result.token);
        this.loading = false;
        this.router.navigate(["/home"]);
        this.userService.getCurrentUser().subscribe(result => {
          this.userService.setUser(result);
        });
      },
      error => {
        this.alertMessage = error.error.message;
        this.showAlert = true;
        this.loading = false;
      }
    );
  }

  ngOnInit() {
    this.email = this.emailInput.nativeElement.value;
    this.password = this.passwordInput.nativeElement.value;
  }
}
