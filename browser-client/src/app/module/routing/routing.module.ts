import { UserInfoSidebarComponent } from "./../../component/user-info/user-info-sidebar/user-info-sidebar.component";
import { UserInfoMainComponent } from "./../../component/user-info/user-info-main/user-info-main.component";
import { UserInfoInterestsComponent } from "./../../component/user-info/user-info-interests/user-info-interests.component";
import { UserInfoContactComponent } from "./../../component/user-info/user-info-contact/user-info-contact.component";
import { UserInfoComponent } from "../../component/user-info/user-info.component";
import { AuthGuard } from "./../../guard/auth/auth.guard";
import { HomeComponent } from "./../../component/home/home.component";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../../component/login/login.component";
import { RegisterComponent } from "../../component/register/register.component";
import { LoggedInGuard } from './../../guard/logged-in/logged-in.guard';

const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "signup", component: RegisterComponent },
  { path: "home", component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: "profile",
    component: UserInfoComponent,
    canActivate: [AuthGuard],
    children: [
      { path: "main", component: UserInfoMainComponent },
      { path: "interests", component: UserInfoInterestsComponent },
      { path: "contact", component: UserInfoContactComponent },
    ],
    canActivateChild: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }

export const components = [
  LoginComponent,
  RegisterComponent,
  HomeComponent,
  UserInfoComponent,
  UserInfoContactComponent,
  UserInfoInterestsComponent,
  UserInfoMainComponent,
  UserInfoSidebarComponent
];
