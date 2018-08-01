export class MonthlyStatistics {
  constructor(
    public id?: string,
    public mysqlUserId?: number,
    public statistics?: any,
    public monthDescription?: string,
    public month?: string,
    public year?: string
  ) {}
}
