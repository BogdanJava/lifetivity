import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from "@angular/core";
import { SidebarTab } from "../../sidebar-tab";
import { User } from '../../../model/user.model';

@Component({
  selector: "app-user-info-main",
  templateUrl: "./user-info-main.component.html",
  styleUrls: ["./user-info-main.component.css"]
})
export class UserInfoMainComponent implements OnInit, SidebarTab {

  public user: User;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(result => {
      this.user = result
    })
  }

  getTabName(): string {
    return "Main Info";
  }
}
