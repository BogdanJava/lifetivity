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

  constructor(
    private userService: UserService,
    public authService: AuthenticationService
  ) {}

  ngOnInit(): void {
    console.log(this.currentUser)
    this.currentUserSubscription = this.userService
      .getCurrentUserObservable()
      .subscribe(user => {
        this.currentUser = user;
      });
    if(this.currentUser == undefined || this.userService == null) {
      this.userService.getCurrentUser().subscribe(result => {
        this.currentUser = result
      })
    }
  }

  ngOnDestroy(): void {
    this.currentUserSubscription.unsubscribe();
  }
}
