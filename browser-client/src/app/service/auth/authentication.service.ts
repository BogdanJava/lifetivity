import { BASE_URL } from "../globals";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  private authUrl: string = BASE_URL + "/auth";

  constructor(private http: HttpClient, private router: Router) { }

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

  public isLoggedIn(): boolean {
    return localStorage.getItem('token') != null;
  }

  public logout(): void {
    localStorage.removeItem('token')
    this.router.navigate(['/login'])
  }
}
