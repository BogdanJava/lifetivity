import { BASE_URL } from "../globals";
import { Injectable, Injector } from "@angular/core";
import { Observable, Subject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  private authUrl: string = BASE_URL + "/auth";
  private infoUrl: string = BASE_URL + "/info";
  private _isLoggedIn = new Subject<boolean>();

  constructor(private http: HttpClient, private router: Router) {}

  public login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.authUrl}/login`, {
      email: email,
      password: password
    });
  }

  public signup(authCredentials: any): Observable<any> {
    return this.http.post(`${this.authUrl}/signup`, authCredentials);
  }

  public isTokenValid(token: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.infoUrl}/check_token_valid`, {
      token: token
    })
  }

  public isEmailReserved(email: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.infoUrl}/check_email_reserved`, {
      email: email
    });
  }

  public setLoggedIn(statement: boolean) {
    this._isLoggedIn.next(statement);
  }

  public isLoggedIn(): Observable<boolean> {
    return this._isLoggedIn.asObservable();
  }

  public logout(): void {
    localStorage.removeItem("token");
    this.setLoggedIn(false);
    this.router.navigate(["/login"]);
  }
}
