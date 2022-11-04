import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";

import {ClientDto} from "../../../dtos/client";

import {ClientsService} from "../services/clients.service";

@Injectable()
export class ClientResolver  implements Resolve<Observable<ClientDto>> {

  constructor(private clientHttpService: ClientsService) {}

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<ClientDto> | Observable<Observable<ClientDto>> | Promise<Observable<ClientDto>> {
    const clientId = route.params["id"];
    return this.clientHttpService.buscarClientePeloId(clientId);
  }

}
