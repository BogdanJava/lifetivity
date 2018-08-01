import { StatisticsKind } from "./../../../../model/stats-kind.model";
import { User } from "./../../../../model/user.model";
import { MonthlyService } from "./../../../../service/monthly/monthly.service";
import { UserService } from "./../../../../service/user/user.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-stat-kinds",
  templateUrl: "./stat-kinds.component.html",
  styleUrls: [
    "./stat-kinds.component.css",
    "../edit-info/edit-info.component.css"
  ]
})
export class StatKindsComponent implements OnInit {
  private user: User;
  public statKinds: StatisticsKind[];
  public kindsExist: boolean;

  constructor(
    private userService: UserService,
    private monthlyService: MonthlyService
  ) {}

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
      this.monthlyService.getStatisticsKindsByUserId(user.id).subscribe(
        kinds => {
          this.kindsExist = true;
          this.statKinds = kinds;
        },
        error => {
          if (error.status == 404) {
            this.kindsExist = false;
          }
        }
      );
    });
  }
}
