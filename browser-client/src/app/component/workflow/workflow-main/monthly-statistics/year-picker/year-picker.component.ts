import { Component, OnInit, Input } from '@angular/core'

@Component({
  selector: 'year-picker',
  templateUrl: './year-picker.component.html',
  styleUrls: ['./year-picker.component.css']
})
export class YearPickerComponent implements OnInit {

  constructor() { }

  @Input() public years: Array<number>

  ngOnInit() {
  }

}
