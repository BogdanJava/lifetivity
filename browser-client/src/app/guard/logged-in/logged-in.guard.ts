import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './../../service/auth/authentication.service';
import { map } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class LoggedInGuard implements CanActivate {

  constructor(private authService: AuthenticationService) { }

  canActivate(): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService
      .isTokenValid(localStorage.getItem("token"))
      .pipe<boolean>(
        map(res => {
          return !res
        })
      );
  }
}
