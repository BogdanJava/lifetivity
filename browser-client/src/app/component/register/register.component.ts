import { Component, OnInit } from "@angular/core";
import { User } from "../../model/user.model";
import { ContactInfo } from "../../model/contact-info.model";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css", "../login/login.component.css"]
})
export class RegisterComponent {
  public user: User;
  public step = 1;

  constructor() {
    this.user = new User();
    this.user.contactInfo = new ContactInfo();
  }

  public onNext(event) {
    console.log(2);
    this.step = 2;
  }

  public onPrev(event) {
    this.step = 1;
  }
}
