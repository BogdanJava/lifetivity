import { StatisticsKind } from "./../../model/stats-kind.model";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BASE_URL } from "../globals";
import { MonthlyStatistics } from "../../model/monthly-statistics.model";

@Injectable({
  providedIn: "root"
})
export class MonthlyService {
  private monthUrl: string = BASE_URL + "/monthly";

  constructor(private http: HttpClient) {}

  getAllByUserId(userId): Observable<MonthlyStatistics[]> {
    return this.http.get<MonthlyStatistics[]>(
      `${this.monthUrl}/by_user_id?userId=${userId}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      }
    );
  }

  getByDate(year, month): Observable<MonthlyStatistics> {
    return this.http.get<MonthlyStatistics>(
      `${this.monthUrl}/by_date?year=${year}&month=${month}`,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`
        }
      }
    );
  }

  getByStatisticsId(id): Observable<MonthlyStatistics> {
    return this.http.get<MonthlyStatistics>(`${this.monthUrl}/by_id?id=${id}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  addStatistics(statistics: MonthlyStatistics): Observable<MonthlyStatistics> {
    return this.http.post<MonthlyStatistics>(this.monthUrl, statistics, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }

  getStatisticsKindsByUserId(userId): Observable<StatisticsKind[]> {
    return this.http.get<StatisticsKind[]>(`${this.monthUrl}/kinds/${userId}`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    });
  }
}
