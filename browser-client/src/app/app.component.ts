import { AuthenticationService } from "./service/auth/authentication.service";
import { Component, OnDestroy, OnInit } from "@angular/core";
import { User } from "./model/user.model";
import { Subscription } from "rxjs";
import { UserService } from "./service/user/user.service";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit, OnDestroy {
  private currentUserSubscription: Subscription;
  public currentUser: User;
  public isLoggedIn: boolean;
  public notificationOptions = {
    position: ["top", "right"],
    timeOut: 3000,
    showProgressBar: true,
    pauseOnHover: true,
    clickToClose: true
  };

  constructor(
    private userService: UserService,
    public authService: AuthenticationService
  ) {}

  ngOnInit(): void {
    this.authService
      .isTokenValid(localStorage.getItem("token"))
      .subscribe(result => {
        this.authService.setLoggedIn(result);
      });
    this.authService.isLoggedIn().subscribe(result => {
      console.log(result);
      this.isLoggedIn = result;
    });
    this.currentUserSubscription = this.userService
      .getCurrentUserObservable()
      .subscribe(user => {
        this.currentUser = user;
      });
    if (this.currentUser == undefined || this.userService == null) {
      this.userService.getCurrentUser().subscribe(result => {
        this.currentUser = result;
      });
    }
  }

  ngOnDestroy(): void {
    this.currentUserSubscription.unsubscribe();
  }
}
