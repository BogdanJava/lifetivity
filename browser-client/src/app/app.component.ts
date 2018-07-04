import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "./service/auth/authentication.service";
import { UserService } from "./service/user/user.service";
import { User } from "./model/user.model";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {

  public currentUser: User

  constructor(
    public authService: AuthenticationService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(result => {
      console.log(result)
      this.currentUser = result
    })
  }
}
