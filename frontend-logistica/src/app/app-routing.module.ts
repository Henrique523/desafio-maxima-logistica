import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {MapComponent} from "./modules/home/views/map/map.component";
import {ClientsComponent} from "./modules/clients/clients.component";

const routes: Routes = [
  { path: "", pathMatch: "full", redirectTo: "/home" },
  { path: "home", component: MapComponent },
  {
    path: "clients",
    component: ClientsComponent,
    children: [
      {
        path: "",
        loadChildren: () => import('./modules/clients/clients.module').then(mod => mod.ClientsModule),
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
