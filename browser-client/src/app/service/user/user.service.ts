import { PageData } from "./../../model/user-page-data.model";
import { Observable, Subject } from "rxjs";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_URL } from "../globals";
import { User } from "../../model/user.model";

@Injectable({
  providedIn: "root"
})
export class UserService {
  private userUrl: string = BASE_URL + "/user";
  private currentUser: Subject<User> = new Subject();

  constructor(private http: HttpClient) {}

  setUser(user: User): void {
    this.currentUser.next(user);
  }

  getCurrentUserObservable(): Observable<User> {
    return this.currentUser.asObservable();
  }

  getUserPageData(): Observable<PageData> {
    return this.http.get<PageData>(`${this.userUrl}/page_data`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  getCurrentUser(): Observable<User> {
    return this.http.get(`${this.userUrl}/me`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  updateStatus(status: string): Observable<any> {
    return this.http.put(`${this.userUrl}/update_status`, null, {
      params: {
        status: status
      },
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  uploadPhoto(formData: FormData): Observable<PageData> {
    return this.http.post<PageData>(
      `${this.userUrl}/upload_profile_photo`,
      formData,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      }
    );
  }

  getProfilePhoto(userId): Observable<any> {
    return this.http.get(`${this.userUrl}/profile_photo?id=${userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`,
        Accept: "application/json"
      }
    });
  }

  isPhotoPresent(userId): Observable<any> {
    return this.http.get(`${this.userUrl}/is_photo_present?id=${userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }
}
