import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInfoInterestsComponent } from './user-info-interests.component';

describe('UserInfoInterestsComponent', () => {
  let component: UserInfoInterestsComponent;
  let fixture: ComponentFixture<UserInfoInterestsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInfoInterestsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInfoInterestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
