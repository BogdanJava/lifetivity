import { UserService } from "./../../../../../service/user/user.service";
import { Component, Input, Output, EventEmitter, OnInit } from "@angular/core";

@Component({
  selector: "app-status-editor",
  templateUrl: "./status-editor.component.html",
  styleUrls: ["./status-editor.component.css"]
})
export class StatusEditorComponent implements OnInit {
  constructor(private userService: UserService) {}

  @Input() public display: boolean;
  @Input() public status: string;
  public newStatus: string;
  @Output()
  public statusChangeEventEmitter: EventEmitter<string> = new EventEmitter();

  changeStatus() {
    this.userService.updateStatus(this.newStatus).subscribe(result => {
      if (result.success) {
        console.log(result.message);
        this.display = false;
        this.statusChangeEventEmitter.emit(result.status);
      }
    });
  }

  ngOnInit(): void {
    this.newStatus = this.status;
  }
}
