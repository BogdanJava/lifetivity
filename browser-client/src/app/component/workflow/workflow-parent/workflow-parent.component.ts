import { Router } from "@angular/router";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-workflow-parent",
  templateUrl: "./workflow-parent.component.html",
  styleUrls: ["./workflow-parent.component.css"]
})
export class WorkflowParentComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit() {
    this.router.navigate(["workflow/welcome"]);
  }
}
