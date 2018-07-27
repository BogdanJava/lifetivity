import { RoutingModule, components } from "./module/routing/routing.module";
import { BrowserModule } from "@angular/platform-browser";
import { NgModule, ErrorHandler } from "@angular/core";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { FileSelectDirective } from "ng2-file-upload";
import { ngfModule } from "angular-file";
import { SimpleNotificationsModule } from "angular2-notifications";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MyDatePickerModule } from "mydatepicker";

import { AppComponent } from "./app.component";
import { SpinnerComponent } from "./component/spinner/spinner.component";
import { GlobalErrorHandler } from "./interceptors/error-handler/error-handler.service";
import { UserPhotoComponent } from "./component/user-info/user-info-main/user-photo/user-photo.component";
import { UserCommonDetailsComponent } from "./component/user-info/user-info-main/user-common-details/user-common-details.component";
import { StatusEditorComponent } from "./component/user-info/user-info-main/user-common-details/status-editor/status-editor.component";
import { RegisterCommonComponent } from './component/register/register-common/register-common.component';
import { RegisterContactComponent } from './component/register/register-contact/register-contact.component';
import { WorkflowCardComponent } from './component/workflow/workflow-main/workflow-card/workflow-card.component';
import { YearPickerComponent } from './component/workflow/workflow-main/monthly-statistics/year-picker/year-picker.component'
import { MonthCarouselComponent } from './component/workflow/workflow-main/monthly-statistics/month-carousel/month-carousel.component'

@NgModule({
  declarations: [
    AppComponent,
    components,
    SpinnerComponent,
    UserPhotoComponent,
    UserCommonDetailsComponent,
    StatusEditorComponent,
    FileSelectDirective,
    RegisterCommonComponent,
    RegisterContactComponent,
    WorkflowCardComponent,
    YearPickerComponent,
    MonthCarouselComponent,
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ngfModule,
    SimpleNotificationsModule.forRoot(),
    BrowserAnimationsModule,
    MyDatePickerModule,
  ],
  providers: [
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
