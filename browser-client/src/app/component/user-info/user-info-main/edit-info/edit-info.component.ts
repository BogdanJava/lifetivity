import { PageData } from "./../../../../model/user-page-data.model";
import { DomSanitizer } from "@angular/platform-browser";
import { UserService } from "./../../../../service/user/user.service";
import { Component, OnInit, Input, ViewChild, ElementRef } from "@angular/core";
import { User } from "../../../../model/user.model";
import { NotificationsService } from "angular2-notifications";
import { NgForm } from "@angular/forms";
import { IMyDpOptions, IMyDate } from "mydatepicker";

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
  public isPhotoPresent: boolean = null;
  public pageData: PageData;
  public photoChanged = false;
  public userUpdated = false;

  private today = new Date();
  public birthday: any;

  public myDpOptions: IMyDpOptions = {
    disableSince: {
      day: this.today.getDate() + 1,
      month: this.today.getMonth() + 1,
      year: this.today.getFullYear()
    },
    disableUntil: {
      day: 1,
      month: 1,
      year: 1917
    },
    dateFormat: "dd-mm-yyyy"
  };

  private selectedFile: File = null;
  private formData: FormData;

  @ViewChild("fileInput") fileInput: ElementRef;
  @ViewChild("preview") previewImgTag: ElementRef;

  ngOnInit(): void {
    this.userService.getCurrentUser().subscribe(result => {
      this.user = result;
      this.userService.getUserPageData(this.user.id).subscribe(result => {
        this.pageData = result;
      });
      let bday = this.user.birthdayDate;
      if (bday) {
        this.birthday = {
          date: {
            year: bday[0],
            month: bday[1],
            day: bday[2]
          }
        };
      }
      this.userService.isPhotoPresent(this.user.id).subscribe(
        result => {
          if (result.status == 200) {
            this.userService.getProfilePhoto(this.user.id).subscribe(result => {
              this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl(
                `data:${result.mimeType};base64, ${result.file}`
              );
              this.isPhotoPresent = true;
            });
          }
        },
        error => {
          if (error.status == 404) {
            console.log(error);
            this.isPhotoPresent = false;
          }
        }
      );
    });
  }

  selectFile() {
    this.isPhotoPresent = true;
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

  onSubmitProfile(form: NgForm) {
    if (this.birthday) {
      let bday = this.birthday.date;
      this.user.birthdayDate = [bday.year, bday.month, bday.day];
    } else {
      this.user.birthdayDate = [];
    }
    if (form.valid) {
      this.userService.updateUser(this.user, this.user.id).subscribe(
        result => {
          this.userService.setUser(result);
          this.user = result;
          this.userUpdated = true;
          setTimeout(() => {
            this.userUpdated = false;
          }, 5000);
        },
        error => {
          this._notifications.alert("User not updated", error);
        }
      );
    }
  }

  saveNewPhoto() {
    this.userService
      .uploadPhoto(this.formData, this.user.id)
      .subscribe(result => {
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
      let reader: any = new FileReader();
      reader.onload = e => {
        this.previewImgTag.nativeElement.setAttribute("src", e.target.result);
        this.photoChanged = true;
      };
      reader.readAsDataURL(this.selectedFile);
    }
  }
}
