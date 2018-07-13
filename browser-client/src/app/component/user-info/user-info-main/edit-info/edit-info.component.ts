import { PageData } from "./../../../../model/user-page-data.model";
import { DomSanitizer } from "@angular/platform-browser";
import { UserService } from "./../../../../service/user/user.service";
import { Component, OnInit, Input, Sanitizer } from "@angular/core";
import { User } from "../../../../model/user.model";

@Component({
  selector: "app-edit-info",
  templateUrl: "./edit-info.component.html",
  styleUrls: [
    "./edit-info.component.css",
    "../user-photo/user-photo.component.css"
  ]
})
export class EditInfoComponent implements OnInit {
  constructor(
    private userService: UserService,
    private _sanitizer: DomSanitizer
  ) {}

  @Input() public user: User;
  public imagePath: any;
  public isPhotoPresent: boolean = false;
  public pageData: PageData;

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(result => {
      this.user = result;
      this.userService.isPhotoPresent(this.user.id).subscribe(result => {
        if (result.status == 200) {
          this.isPhotoPresent = true;
          this.userService.getProfilePhoto(this.user.id).subscribe(result => {
            this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl(
              `data:${result.mimeType};base64, ${result.file}`
            );
          });
        } else if (result.status == 404) {
          this.isPhotoPresent = false;
        }
      });
    });
    this.userService.getUserPageData().subscribe(result => {
      this.pageData = result;
    });
  }
}
