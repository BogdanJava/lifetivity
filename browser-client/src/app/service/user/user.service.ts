import { PageData } from './../../model/user-page-data.model';
import { Observable, Subject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_URL } from "../globals";
import { User } from "../../model/user.model";

@Injectable({
  providedIn: "root"
})
export class UserService {
  private userUrl: string = BASE_URL + "/user";
  private headers = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`
    }
  };

  private currentUser: Subject<User> = new Subject();

  constructor(private http: HttpClient) {}

  setUser(user: User): void {
    this.currentUser.next(user);
  }

  getCurrentUserObservable(): Observable<User> {
    return this.currentUser.asObservable();
  }

  getUserPageData(): Observable<PageData> {
    return this.http.get<PageData>(`${this.userUrl}/page_data`, this.headers);
  }

  getCurrentUser(): Observable<User> {
    return this.http.get(`${this.userUrl}/me`, this.headers);
  }
}
