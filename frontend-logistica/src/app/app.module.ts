import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from "@angular/material/form-field";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {AppComponent} from './app.component';
import {ToolbarButtonModule} from "./components/toolbar-button/toolbar-button.module";
import {AppRoutingModule} from "./app-routing.module";
import {MaterialModule} from "./material/material.module";
import {ListModule as ClientsListModule} from "./modules/clients/views/list/list.module";
import {HttpClientModule} from "@angular/common/http";
import {MapModule} from "./modules/home/views/map/map.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MaterialModule,
    ToolbarButtonModule,
    ClientsListModule,
    MapModule,
  ],
  providers: [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'outline' } },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
