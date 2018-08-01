import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatKindsComponent } from './stat-kinds.component';

describe('StatKindsComponent', () => {
  let component: StatKindsComponent;
  let fixture: ComponentFixture<StatKindsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatKindsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatKindsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
