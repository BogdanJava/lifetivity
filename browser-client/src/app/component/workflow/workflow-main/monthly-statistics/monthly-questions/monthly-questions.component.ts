import { when } from "q";
import { MonthlyService } from "./../../../../../service/monthly/monthly.service";
import { Component, OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { monthNames } from "../../../../../service/globals";
import { Subscription } from "rxjs";
import { UserService } from "../../../../../service/user/user.service";
import { User } from "../../../../../model/user.model";
import { MonthlyStatistics } from "../../../../../model/monthly-statistics.model";

@Component({
  selector: "app-monthly-questions",
  templateUrl: "./monthly-questions.component.html",
  styleUrls: ["./monthly-questions.component.css"]
})
export class MonthlyQuestionsComponent implements OnInit, OnDestroy {
  public year: number;
  public month: any;
  private sub: Subscription;
  private user: User;
  public statistics: MonthlyStatistics;
  public statsPresent = false;

  constructor(
    private route: ActivatedRoute,
    private monthlyService: MonthlyService,
    private userService: UserService
  ) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.year = +params["year"];
      this.month = monthNames[+params["month"] - 1];
    });
    this.userService.getCurrentUser().subscribe(user => {
      this.user = user;
      this.monthlyService.getByDate(this.year, this.month.id).subscribe(
        stats => {
          this.statistics = stats;
          this.statsPresent = true;
        },
        error => {
          if (error.status == 404) {
            this.statsPresent = false;
          }
        }
      );
    });
  }

  saveStatistics() {}

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
