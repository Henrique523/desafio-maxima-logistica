import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {MaterialModule} from "../../../../material/material.module";

import {ClientResolver} from "../../resolvers/client.resolver";

import { CreateOrUpdateComponent } from './create-or-update.component';

@NgModule({
  declarations: [CreateOrUpdateComponent],
  imports: [CommonModule, MaterialModule, FormsModule, ReactiveFormsModule],
  exports: [CreateOrUpdateComponent],
  providers: [ClientResolver],
})
export class CreateOrUpdateModule { }
