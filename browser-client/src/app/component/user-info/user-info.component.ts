import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-user-info",
  templateUrl: "./user-info.component.html",
  styleUrls: ["./user-info.component.css"]
})
export class UserInfoComponent {
  public tabName: string;

  constructor() {}

  setTabName(currentTab) {
    this.tabName = currentTab.getTabName();
  }

}
