import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from 'events';
import { SidebarTab } from '../../sidebar-tab';

@Component({
  selector: 'app-user-info-sidebar',
  templateUrl: './user-info-sidebar.component.html',
  styleUrls: ['./user-info-sidebar.component.css']
})
export class UserInfoSidebarComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
