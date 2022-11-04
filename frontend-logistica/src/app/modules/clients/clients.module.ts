import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ClientsComponent} from './clients.component';
import {ClientsRoutingModule} from "./clients-routing.module";
import {CreateOrUpdateModule} from "./views/create-or-update/create-or-update.module";
import {ListModule} from "./views/list/list.module";

@NgModule({
  declarations: [ClientsComponent],
  imports: [CommonModule, ClientsRoutingModule, CreateOrUpdateModule, ListModule],
  exports: [ClientsComponent]
})
export class ClientsModule { }
