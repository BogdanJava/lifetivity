import { PageData } from "./../../../../model/user-page-data.model";
import { Component, OnInit, Input, ViewChild, ElementRef } from "@angular/core";
import { User } from "../../../../model/user.model";

@Component({
  selector: "app-user-common-details",
  templateUrl: "./user-common-details.component.html",
  styleUrls: ["./user-common-details.component.css"]
})
export class UserCommonDetailsComponent implements OnInit {
  constructor() {}

  @Input() public user: User;
  @Input() public pageData: PageData;
  public displayStatusEditor: boolean = false;

  ngOnInit() {
  }

  toggleStatusEditor() {
    this.displayStatusEditor = !this.displayStatusEditor;
  }

  onStatusChange(status) {
    this.pageData.status = status;
  }
}
