import { SafeUrl } from "@angular/platform-browser";
import { DomSanitizer } from "@angular/platform-browser";
import { Component, OnInit, ViewChild, ElementRef, Input } from "@angular/core";

@Component({
  selector: "workflow-card",
  templateUrl: "./workflow-card.component.html",
  styleUrls: ["./workflow-card.component.css"]
})
export class WorkflowCardComponent implements OnInit {
  constructor(private _sanitizer: DomSanitizer) {}

  @Input() cardHeaderBgColor: string;
  @Input() cardHeaderText: string;
  @Input() cardBodyText: string;
  @Input() cardImageUrl: string;
  public securedCardImageUrl: SafeUrl;

  @ViewChild("bodyText") private bodyText: ElementRef;
  @ViewChild("bodyIcon") private bodyIcon: ElementRef;

  ngOnInit() {
    this.securedCardImageUrl = this._sanitizer.bypassSecurityTrustStyle(
      `url(${this.cardImageUrl})`
    );
    console.log(this.securedCardImageUrl);
    let textDiv: HTMLElement = this.bodyText.nativeElement;
    let bodyIconDiv: HTMLDivElement = this.bodyIcon.nativeElement;
    textDiv.addEventListener("mouseenter", e => {
      bodyIconDiv.classList.add("body-icon-hover");
    });
    textDiv.addEventListener("mouseleave", e => {
      bodyIconDiv.classList.remove("body-icon-hover");
    });
  }

  public click() {

  }
}
