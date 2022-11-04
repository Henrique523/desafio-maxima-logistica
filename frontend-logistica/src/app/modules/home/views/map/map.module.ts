import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GoogleMapsModule} from '@angular/google-maps';
import {MapComponent} from './map.component';
import {BrowserModule} from "@angular/platform-browser";
import {MaterialModule} from "../../../../material/material.module";
import {HttpClientJsonpModule, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
// import {CnpjTransformerPipe} from "../../../../pipes/cnpj-transformer-pipe";

@NgModule({
  declarations: [MapComponent],
  imports: [
    BrowserModule,
    CommonModule,
    MaterialModule,
    HttpClientModule,
    HttpClientJsonpModule,
    GoogleMapsModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  exports: [MapComponent],
})
export class MapModule { }
