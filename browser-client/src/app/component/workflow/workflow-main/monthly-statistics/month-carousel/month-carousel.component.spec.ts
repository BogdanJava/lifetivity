import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthCarouselComponent } from './month-carousel.component';

describe('MonthCarouselComponent', () => {
  let component: MonthCarouselComponent;
  let fixture: ComponentFixture<MonthCarouselComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthCarouselComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthCarouselComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
