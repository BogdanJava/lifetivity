import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from "@angular/core";
import { SidebarTab } from "../../sidebar-tab";

@Component({
  selector: "app-user-info-main",
  templateUrl: "./user-info-main.component.html",
  styleUrls: ["./user-info-main.component.css"]
})
export class UserInfoMainComponent implements OnInit, SidebarTab {

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(result => {
      console.log(result)
    })
  }

  getTabName(): string {
    return "Main Info";
  }
}
