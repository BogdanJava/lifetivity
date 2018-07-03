import { Injectable } from "@angular/core";
import { CanActivate, Router, CanActivateChild } from "@angular/router";
import { AuthenticationService } from "../../service/auth/authentication.service";
import { map } from "rxjs/operators";
import { Observable, range } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AuthGuard implements CanActivate, CanActivateChild {
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService
      .isTokenValid(localStorage.getItem("token"))
      .pipe<boolean>(
        map(res => {
          if (res) {
            return true;
          } else {
            this.router.navigate(["/login"]);
            return false;
          }
        })
      );
  }

  canActivateChild(): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService
      .isTokenValid(localStorage.getItem("token"))
      .pipe<boolean>(
        map(res => {
          if (res) {
            return true;
          } else {
            this.router.navigate(["/login"]);
            return false;
          }
        })
      );
  }
}
