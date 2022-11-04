import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ListComponent} from './list.component';
import {MaterialModule} from "../../../../material/material.module";
import {RouterLink} from "@angular/router";
import {CnpjTransformerPipe} from "../../../../pipes/cnpj-transformer-pipe";


@NgModule({
  declarations: [ListComponent, CnpjTransformerPipe],
  imports: [CommonModule, MaterialModule, RouterLink],
  exports: [ListComponent],
})
export class ListModule { }
