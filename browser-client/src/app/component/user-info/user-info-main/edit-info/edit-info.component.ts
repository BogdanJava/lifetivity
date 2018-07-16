import { PageData } from "./../../../../model/user-page-data.model";
import { DomSanitizer } from "@angular/platform-browser";
import { UserService } from "./../../../../service/user/user.service";
import { Component, OnInit, Input, ViewChild, ElementRef } from "@angular/core";
import { User } from "../../../../model/user.model";
import { NotificationsService } from "angular2-notifications";

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
    private _sanitizer: DomSanitizer,
    private _notifications: NotificationsService
  ) {}

  @Input() public user: User;
  public imagePath: any;
  public isPhotoPresent: boolean = false;
  public pageData: PageData;
  private formData: FormData;
  @ViewChild("fileInput") fileInput: ElementRef;
  @ViewChild("preview") previewImgTag: ElementRef;
  public photoChanged = false;
  private selectedFile: File = null;

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

  selectFile() {
    this.fileInput.nativeElement.click();
  }

  onSelectFile(event) {
    let fileList: FileList = event.target.files;
    let file = fileList.item(0);
    this.selectedFile = file;
    if (file) {
      this.formData = new FormData();
      this.formData.append("file", file, file.name);
      this.previewPhoto();
    }
  }

  saveNewPhoto() {
    this.userService.uploadPhoto(this.formData).subscribe(result => {
      if (result.success) {
        this.userService.getProfilePhoto(this.user.id).subscribe(result => {
          this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl(
            `data:${result.mimeType};base64, ${result.file}`
          );
          this._notifications.info(
            "Success",
            "Photo has been successfully changed"
          );
          this.cancelNewPhoto();
        });
      }
    });
  }

  cancelNewPhoto() {
    this.photoChanged = false;
    this.formData = null;
    this.selectedFile = null;
  }

  previewPhoto() {
    let file = this.formData.get("file");
    if (file != null) {
      let reader = new FileReader();
      reader.onload = e => {
        this.previewImgTag.nativeElement.setAttribute("src", e.target.result);
        this.photoChanged = true;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
}
