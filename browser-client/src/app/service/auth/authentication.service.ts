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
  private _isLoggedIn = new Subject<boolean>();
  private authService: AuthenticationService;

  constructor(
    private http: HttpClient,
    private router: Router,
    private injector: Injector
  ) {
    this.authService = this.injector.get(AuthenticationService);
  }

  public login(email: string, password: string): Observable<any> {
    return this.http.post(`${this.authUrl}/login`, {
      email: email,
      password: password
    });
  }

  public signup(email: string, password: string): Observable<any> {
    return this.http.post(`${this.authUrl}/signup`, {
      email: email,
      password: password
    });
  }

  public isTokenValid(token: string): Observable<boolean> {
    return this.http.post<boolean>(`${this.authUrl}/check_token_valid`, {
      token: token
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
