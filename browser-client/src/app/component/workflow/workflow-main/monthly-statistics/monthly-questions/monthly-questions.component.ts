import { Component, OnInit, OnDestroy } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { monthNames } from "src/app/service/globals";
import { Subscription } from "rxjs";

@Component({
  selector: "app-monthly-questions",
  templateUrl: "./monthly-questions.component.html",
  styleUrls: ["./monthly-questions.component.css"]
})
export class MonthlyQuestionsComponent implements OnInit, OnDestroy {
  public year: number;
  public month: any;
  private sub: Subscription;

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.year = +params["year"];
      this.month = monthNames[+params["month"] - 1];
      console.log(this.year, this.month);
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
