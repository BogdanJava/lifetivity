import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInfoMainComponent } from './user-info-main.component';

describe('UserInfoMainComponent', () => {
  let component: UserInfoMainComponent;
  let fixture: ComponentFixture<UserInfoMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInfoMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInfoMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
