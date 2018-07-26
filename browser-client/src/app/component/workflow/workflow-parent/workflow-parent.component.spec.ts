import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowParentComponent } from './workflow-parent.component';

describe('WorkflowParentComponent', () => {
  let component: WorkflowParentComponent;
  let fixture: ComponentFixture<WorkflowParentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowParentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowParentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
