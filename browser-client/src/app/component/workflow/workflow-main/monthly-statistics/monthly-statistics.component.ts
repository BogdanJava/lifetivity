import { range } from "rxjs";
import { Component, OnInit } from "@angular/core";
import { UserService } from "../../../../service/user/user.service";
import { User } from "../../../../model/user.model";

@Component({
  selector: "app-monthly-statistics",
  templateUrl: "./monthly-statistics.component.html",
  styleUrls: [
    "./monthly-statistics.component.css",
    "../workflow-main.component.css"
  ]
})
export class MonthlyStatisticsComponent implements OnInit {
  constructor(private userService: UserService) {}

  public user: User;
  public years = new Array<number>();

  ngOnInit() {
    this.userService.getCurrentUserObservable().subscribe(user => {
      this.user = user;
    });
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
      let userYear = this.user.birthdayDate[0];
      range(userYear, new Date().getFullYear() + 1 - userYear).forEach(y => {
        this.years.push(y);
      });
      console.log(this.years);
    });
  }
}
