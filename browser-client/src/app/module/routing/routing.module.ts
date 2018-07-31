import { MonthlyStatisticsComponent } from './../../component/workflow/workflow-main/monthly-statistics/monthly-statistics.component';
import { QuestionnaireComponent } from './../../component/workflow/workflow-main/questionnaire/questionnaire.component';
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
import { WorkflowParentComponent } from "../../component/workflow/workflow-parent/workflow-parent.component";
import { ChartsMainComponent } from '../../component/workflow/workflow-main/charts-main/charts-main.component';
import { MonthlyQuestionsComponent } from '../../component/workflow/workflow-main/monthly-statistics/monthly-questions/monthly-questions.component';

const routes: Routes = [
  { path: "", redirectTo: "/login", pathMatch: "full" },
  { path: "login", component: LoginComponent, canActivate: [LoggedInGuard] },
  {
    path: "signup",
    component: RegisterComponent,
    canActivate: [LoggedInGuard]
  },
  { path: "home", component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: "workflow",
    component: WorkflowParentComponent,
    children: [
      { path: "welcome", component: WorkflowComponent },
      { path: "main", component: WorkflowMainComponent },
      { path: "questions", component: QuestionnaireComponent },
      { path: "charts", component: ChartsMainComponent },
      { path: "monthly", component: MonthlyStatisticsComponent },
      { path: "monthly/:year/:month", component: MonthlyQuestionsComponent }
    ],
    canActivate: [AuthGuard],
    canActivateChild: [AuthGuard]
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
  WorkflowMainComponent,
  WorkflowParentComponent,
  MonthlyStatisticsComponent,
  QuestionnaireComponent,
  ChartsMainComponent,
  MonthlyQuestionsComponent
];
