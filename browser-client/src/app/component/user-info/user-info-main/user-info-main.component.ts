import { PageData } from './../../../model/user-page-data.model';
import { UserService } from './../../../service/user/user.service';
import { Component, OnInit } from "@angular/core";
import { User } from '../../../model/user.model';

@Component({
  selector: "app-user-info-main",
  templateUrl: "./user-info-main.component.html",
  styleUrls: ["./user-info-main.component.css"]
})
export class UserInfoMainComponent implements OnInit {

  public user: User;
  public pageData: PageData;

  constructor(private userService: UserService) {}

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(result => {
      this.user = result
    })
    this.userService.getUserPageData().subscribe(result => {
      this.pageData = result;
    })
  }

}
