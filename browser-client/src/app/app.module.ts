import { RoutingModule, components } from './module/routing/routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SpinnerComponent } from './component/spinner/spinner.component';
import { GlobalErrorHandler } from './interceptors/error-handler/error-handler.service';
import { UserPhotoComponent } from './component/user-info/user-info-main/user-photo/user-photo.component';
import { UserCommonDetailsComponent } from './component/user-info/user-info-main/user-common-details/user-common-details.component';

@NgModule({
  declarations: [
    AppComponent,
    components,
    SpinnerComponent,
    UserPhotoComponent,
    UserCommonDetailsComponent,
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
