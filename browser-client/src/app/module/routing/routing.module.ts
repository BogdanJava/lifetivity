import { WorkflowComponent } from "./../../component/workflow/workflow.component";
import { EditInfoComponent } from "./../../component/user-info/user-info-main/edit-info/edit-info.component";
import { UserInfoSidebarComponent } from "./../../component/user-info/user-info-main/user-info-sidebar/user-info-sidebar.component";
import { UserInfoMainComponent } from "./../../component/user-info/user-info-main/user-info-main.component";
import { UserInfoComponent } from "../../component/user-info/user-info.component";
import { AuthGuard } from "./../../guard/auth/auth.guard";
import { HomeComponent } from "./../../component/home/home.component";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../../component/login/login.component";
import { RegisterComponent } from "../../component/register/register.component";
import { LoggedInGuard } from "./../../guard/logged-in/logged-in.guard";
import { EditContactInfoComponent } from "../../component/user-info/user-info-main/edit-info/edit-contact-info/edit-contact-info.component";
import { WorkflowMainComponent } from "../../component/workflow/workflow-main/workflow-main.component";

const routes: Routes = [
  { path: "login", component: LoginComponent, canActivate: [LoggedInGuard] },
  {
    path: "signup",
    component: RegisterComponent,
    canActivate: [LoggedInGuard]
  },
  { path: "home", component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: "workflow",
    component: WorkflowComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "workflow/main",
    component: WorkflowMainComponent,
    canActivate: [AuthGuard]
  },
  {
    path: "profile",
    component: UserInfoComponent,
    canActivate: [AuthGuard],
    children: [
      { path: "main", component: UserInfoMainComponent },
      { path: "edit", redirectTo: "edit/common" },
      { path: "edit/common", component: EditInfoComponent },
      { path: "edit/contact", component: EditContactInfoComponent }
    ],
    canActivateChild: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule {}

export const components = [
  LoginComponent,
  RegisterComponent,
  HomeComponent,
  UserInfoComponent,
  UserInfoMainComponent,
  UserInfoSidebarComponent,
  EditInfoComponent,
  EditContactInfoComponent,
  WorkflowComponent,
  WorkflowMainComponent
];
