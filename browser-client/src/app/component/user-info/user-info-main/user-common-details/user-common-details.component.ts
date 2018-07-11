import { PageData } from './../../../../model/user-page-data.model';
import { Component, OnInit, Input } from '@angular/core';
import { User } from '../../../../model/user.model';
import { UserService } from '../../../../service/user/user.service';

@Component({
  selector: 'app-user-common-details',
  templateUrl: './user-common-details.component.html',
  styleUrls: ['./user-common-details.component.css']
})
export class UserCommonDetailsComponent implements OnInit {

  constructor(private userService: UserService) { }

  @Input() public user: User;
  public pageData: PageData;

  ngOnInit() {
    this.userService.getUserPageData().subscribe(result => {
      this.pageData = result;
    })
  }

}
