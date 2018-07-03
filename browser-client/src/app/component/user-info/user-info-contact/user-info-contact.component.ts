import { Component, OnInit } from '@angular/core';
import { SidebarTab } from '../../sidebar-tab';

@Component({
  selector: 'app-user-info-contact',
  templateUrl: './user-info-contact.component.html',
  styleUrls: ['./user-info-contact.component.css']
})
export class UserInfoContactComponent implements OnInit, SidebarTab {

  constructor() { }

  ngOnInit() {
  }

  getTabName(): string {
    return "Contact Info";
  }

}
