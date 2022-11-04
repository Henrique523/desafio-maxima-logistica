import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {ListComponent} from "./views/list/list.component";
import {CreateOrUpdateComponent} from "./views/create-or-update/create-or-update.component";
import {ClientResolver} from "./resolvers/client.resolver";

const routes: Routes = [
  {
    path: "",
    component: ListComponent
  },
  {
    path: "create",
    component: CreateOrUpdateComponent,
  },
  {
    path: "update/:id",
    component: CreateOrUpdateComponent,
    resolve: { client: ClientResolver },
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ClientsRoutingModule { }
