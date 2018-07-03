import { Component, OnInit } from '@angular/core';
import { SidebarTab } from '../../sidebar-tab';

@Component({
  selector: 'app-user-info-interests',
  templateUrl: './user-info-interests.component.html',
  styleUrls: ['./user-info-interests.component.css']
})
export class UserInfoInterestsComponent implements OnInit, SidebarTab {

  constructor() { }

  ngOnInit() {
  }

  getTabName(): string {
    return "Interests";
  }

}
