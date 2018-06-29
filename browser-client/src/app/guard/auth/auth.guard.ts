import { Injectable } from "@angular/core";
import { CanActivate, Router } from "@angular/router";
import { AuthenticationService } from "../../service/auth/authentication.service";
import { map, catchError } from "rxjs/operators";
import { Observable, range } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthenticationService,
    private router: Router
  ) {}

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    range(0, 100);
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
