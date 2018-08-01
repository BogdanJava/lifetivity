import { PageData } from "../../model/user-page-data.model";
import { Observable, Subject } from "rxjs";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_URL } from "../globals";
import { User } from "../../model/user.model";
import { ContactInfo } from "../../model/contact-info.model";

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

  getCurrentUserPromise(): Promise<User> {
    return this.currentUser.toPromise();
  }

  getUserPageData(userId): Observable<PageData> {
    return this.http.get<PageData>(`${this.userUrl}/page_data/${userId}`, {
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

  updateStatus(status: string, userId): Observable<any> {
    return this.http.put(`${this.userUrl}/update_status/${userId}`, null, {
      params: {
        status: status
      },
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  updateUserContactInfo(contactInfo: ContactInfo, userId): Observable<any> {
    return this.http.put(`${this.userUrl}/update_contact_info/${userId}`, contactInfo, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  updateUser(user: User, usedId): Observable<any> {
    return this.http.put<any>(`${this.userUrl}?userId=${usedId}`, user, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  uploadPhoto(formData: FormData, userId): Observable<any> {
    return this.http.post<any>(
      `${this.userUrl}/upload_profile_photo/${userId}`,
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

  getUserContactInfo(userId): Observable<any> {
    return this.http.get<any>(`${this.userUrl}/contact_info/${userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
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
