import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserCommonDetailsComponent } from './user-common-details.component';

describe('UserCommonDetailsComponent', () => {
  let component: UserCommonDetailsComponent;
  let fixture: ComponentFixture<UserCommonDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserCommonDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserCommonDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
