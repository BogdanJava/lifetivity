import { ContactInfo } from "./contact-info.model";

export class User {
  constructor(
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public birthdayDate?: number[],
    public registrationDate?: number[],
    public lastLoggedInDateTime?: number[],
    public contactInfo?: ContactInfo,
    public role?: string
  ) {}
}
