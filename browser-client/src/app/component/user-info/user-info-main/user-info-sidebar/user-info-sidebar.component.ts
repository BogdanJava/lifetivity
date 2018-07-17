import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-user-info-sidebar",
  templateUrl: "./user-info-sidebar.component.html",
  styleUrls: ["./user-info-sidebar.component.css"]
})
export class UserInfoSidebarComponent implements OnInit {
  constructor() {}

  ngOnInit() {}

  isSubTabShown(routeNames: string[]) {
    let url = window.location.href;
    let route = url.substring(url.lastIndexOf("/") + 1, url.length);
    for (let name of routeNames) {
      if (route == name) return true;
    }
    return false;
  }
}
