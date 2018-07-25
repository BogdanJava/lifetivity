import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";

@Component({
  selector: "app-workflow",
  templateUrl: "./workflow.component.html",
  styleUrls: ["./workflow.component.css"]
})
export class WorkflowComponent implements OnInit {
  constructor() {}

  @ViewChild("mainContent") private mainContent: ElementRef;

  ngOnInit() {
    this.mainContentApproximation(this.mainContent.nativeElement);
  }

  private mainContentApproximation(div: HTMLElement) {
    if (div) {
      setTimeout(() => {
        let counter: number = 0;
        setInterval(() => {
          if (counter == 500) return;
          let bgSize = div.style.backgroundSize;
          div.style.backgroundSize = `${Number(
            bgSize.substring(0, bgSize.length - 2)
          ) + 1}px`;
          console.log(bgSize);
          counter++;
        }, 100);
      }, 200);
    }
  }
}
