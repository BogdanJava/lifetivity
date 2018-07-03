import { AuthGuard } from "./../../guard/auth/auth.guard";
import { HomeComponent } from "./../../component/home/home.component";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { LoginComponent } from "../../component/login/login.component";
import { RegisterComponent } from "../../component/register/register.component";
import { LoggedInGuard } from './../../guard/logged-in/logged-in.guard';

const routes: Routes = [
  { path: "login", component: LoginComponent, canActivate: [LoggedInGuard] },
  { path: "signup", component: RegisterComponent, canActivate: [LoggedInGuard] },
  { path: "home", component: HomeComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }

export const routeComponents = [
  LoginComponent,
  RegisterComponent,
  HomeComponent
];
