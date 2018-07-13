import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-user-info-sidebar",
  templateUrl: "./user-info-sidebar.component.html",
  styleUrls: ["./user-info-sidebar.component.css"]
})
export class UserInfoSidebarComponent implements OnInit {
  constructor() {}

  ngOnInit() {
  }

  getCurrentTabName() {
    let url = window.location.href;
    return url.substring(url.lastIndexOf("/") + 1, url.length);
  }
}
