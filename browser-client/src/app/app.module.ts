import { RoutingModule, components } from './module/routing/routing.module';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { SpinnerComponent } from './component/spinner/spinner.component';

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< HEAD
    routeComponents,
    SpinnerComponent
=======
    components,
    SpinnerComponent,
>>>>>>> c8cf0d36732aa70761ca829c79c7bcac2a8272e2
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
