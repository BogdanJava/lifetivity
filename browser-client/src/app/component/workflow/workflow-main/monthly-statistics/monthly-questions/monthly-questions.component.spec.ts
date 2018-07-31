import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyQuestionsComponent } from './monthly-questions.component';

describe('MonthlyQuestionsComponent', () => {
  let component: MonthlyQuestionsComponent;
  let fixture: ComponentFixture<MonthlyQuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MonthlyQuestionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlyQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
