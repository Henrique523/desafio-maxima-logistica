import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterLink} from "@angular/router";

import {ToolbarButtonComponent} from './toolbar-button.component';
import {MaterialModule} from "../../material/material.module";

@NgModule({
  declarations: [ToolbarButtonComponent],
  imports: [
    CommonModule,
    MaterialModule,
    RouterLink
  ],
  exports: [ToolbarButtonComponent]
})
export class ToolbarButtonModule {}
