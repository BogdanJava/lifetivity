import { Router } from "@angular/router";
import { Injectable, ErrorHandler, Injector } from "@angular/core";

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {
  constructor(private injector: Injector) {
  }

  handleError(error: any): void {
    let router = this.getRouter()
    if (error.status == 401) {
      console.log("Unauthorized");
      router.navigate(["/login"]);
      console.log('here')
    } else {
      throw error;
    }
  }

  getRouter(): Router {
    return this.injector.get(Router)
  }

}
