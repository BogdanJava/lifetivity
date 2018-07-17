import { DomSanitizer, SafeUrl } from "@angular/platform-browser";
import { PageData } from "./../../../../model/user-page-data.model";
import { Component, OnInit, Input } from "@angular/core";
import { User } from "../../../../model/user.model";

@Component({
  selector: "app-user-common-details",
  templateUrl: "./user-common-details.component.html",
  styleUrls: ["./user-common-details.component.css"]
})
export class UserCommonDetailsComponent implements OnInit {
  constructor(public sanitizer: DomSanitizer) {}

  @Input() public user: User;
  @Input() public pageData: PageData;
  public displayStatusEditor: boolean = false;
  public trustedSkypeUrl: SafeUrl;

  ngOnInit() {
    if (this.user != null && this.user.contactInfo != null) {
      let skype = this.user.contactInfo.skypeAccount;
      if (skype) {
        this.trustedSkypeUrl = this.makeTrusted(`skype:${skype}`);
      }
    }
  }

  toggleStatusEditor() {
    this.displayStatusEditor = !this.displayStatusEditor;
  }

  onStatusChange(status) {
    this.pageData.status = status;
  }

  public makeTrusted(str: string): SafeUrl {
    return this.sanitizer.bypassSecurityTrustUrl(str);
  }
}
