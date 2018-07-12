import { RoutingModule, components } from './module/routing/routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { FileSelectDirective } from 'ng2-file-upload';
import { ngfModule } from "angular-file"

import { AppComponent } from './app.component';
import { SpinnerComponent } from './component/spinner/spinner.component';
import { GlobalErrorHandler } from './interceptors/error-handler/error-handler.service';
import { UserPhotoComponent } from './component/user-info/user-info-main/user-photo/user-photo.component';
import { UserCommonDetailsComponent } from './component/user-info/user-info-main/user-common-details/user-common-details.component';
import { StatusEditorComponent } from './component/user-info/user-info-main/user-common-details/status-editor/status-editor.component';

@NgModule({
  declarations: [
    AppComponent,
    components,
    SpinnerComponent,
    UserPhotoComponent,
    UserCommonDetailsComponent,
    StatusEditorComponent,
    FileSelectDirective
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ngfModule
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
