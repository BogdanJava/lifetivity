import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_URL } from "../globals";
import { User } from "../../model/user.model";

@Injectable({
  providedIn: "root"
})
export class UserService {
  private userUrl: string = BASE_URL + "/user";

  constructor(private http: HttpClient) {}

  getCurrentUser(): Observable<User> {
    return this.http.get(this.userUrl + "/me", {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }
}
