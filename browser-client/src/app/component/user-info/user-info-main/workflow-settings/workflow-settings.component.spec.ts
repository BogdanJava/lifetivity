import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowSettingsComponent } from './workflow-settings.component';

describe('WorkflowSettingsComponent', () => {
  let component: WorkflowSettingsComponent;
  let fixture: ComponentFixture<WorkflowSettingsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowSettingsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowSettingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
