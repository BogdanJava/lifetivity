import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInfoContactComponent } from './user-info-contact.component';

describe('UserInfoContactComponent', () => {
  let component: UserInfoContactComponent;
  let fixture: ComponentFixture<UserInfoContactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInfoContactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInfoContactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
