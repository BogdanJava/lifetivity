import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowMainComponent } from './workflow-main.component';

describe('WorkflowMainComponent', () => {
  let component: WorkflowMainComponent;
  let fixture: ComponentFixture<WorkflowMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
