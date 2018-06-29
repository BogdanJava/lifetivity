import { BASE_URL } from "../globals";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  private authUrl: string;

  constructor(private http: HttpClient) {
    this.authUrl = BASE_URL + "/auth";
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
}
