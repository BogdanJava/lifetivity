import { OnInit, Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'year-picker',
  templateUrl: './year-picker.component.html',
  styleUrls: [
    './year-picker.component.css'
  ]
})
export class YearPickerComponent implements OnInit {

  //todo: pagination?

  @Input() public years: Array<number>
  @Output() public year = new EventEmitter<number>()

  constructor() {}

  ngOnInit(): void {
  }

  public chooseYear(e) {
    this.year.emit(e.target.value)
  }

}
