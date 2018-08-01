import { User } from "../../../../model/user.model";
import { UserService } from "../../../../service/user/user.service";
import { PageData } from "../../../../model/user-page-data.model";
import { Component, Input, ElementRef, ViewChild, OnInit } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";

@Component({
  selector: "app-user-photo",
  templateUrl: "./user-photo.component.html",
  styleUrls: ["./user-photo.component.css"]
})
export class UserPhotoComponent implements OnInit {
  constructor(
    private userService: UserService,
    private _sanitizer: DomSanitizer
  ) {}

  @Input() public user: User;
  @Input() public pageData: PageData;
  @ViewChild("fileInput") fileInput: ElementRef;
  @ViewChild("uploadText") uploadText: ElementRef;
  public isPhotoPresent: boolean = false;
  public imagePath: any;

  selectFile() {
    this.fileInput.nativeElement.click();
  }

  onSelectFile(event) {
    let fileList: FileList = event.target.files;
    let file = fileList.item(0);
    if (file) {
      let formData: FormData = new FormData();
      formData.append("file", file, file.name);
      this.userService.uploadPhoto(formData, this.user.id).subscribe(result => {
        console.log(result);
      });
    }
  }

  ngOnInit(): void {
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
  }
}
