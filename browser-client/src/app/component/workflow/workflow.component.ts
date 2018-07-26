import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";

@Component({
  selector: "app-workflow",
  templateUrl: "./workflow.component.html",
  styleUrls: ["./workflow.component.css"]
})
export class WorkflowComponent implements OnInit {
  constructor() { }

  @ViewChild('background') private bgDiv: ElementRef

  ngOnInit() {
    setTimeout(() => {
      let div: HTMLElement = this.bgDiv.nativeElement
      if(div) {
        div.classList.add('background-scalable')
      }
    }, 100)
  }
}
