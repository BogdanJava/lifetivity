import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowCardComponent } from './workflow-card.component';

describe('WorkflowCardComponent', () => {
  let component: WorkflowCardComponent;
  let fixture: ComponentFixture<WorkflowCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
