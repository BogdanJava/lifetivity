import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { SidebarTab } from '../sidebar-tab';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  public tabName: string;

  constructor(private router: Router) { }

  ngOnInit() {
    this.router.navigate(['/profile/main'])
  }

  setTabName(currentTab: SidebarTab) {
    this.tabName = currentTab.getTabName();
  }

}
