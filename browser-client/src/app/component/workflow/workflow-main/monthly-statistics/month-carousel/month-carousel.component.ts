import { Component, OnInit, Input } from "@angular/core";

@Component({
  selector: "month-carousel",
  templateUrl: "./month-carousel.component.html",
  styleUrls: [
    "./month-carousel.component.css",
    "../year-picker/year-picker.component.css"
  ]
})
export class MonthCarouselComponent implements OnInit {
  public months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
  ];

  @Input() public year: number;

  constructor() {}

  ngOnInit() {}
}
