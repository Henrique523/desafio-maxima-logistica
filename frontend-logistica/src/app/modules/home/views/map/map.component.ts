import { Component, OnInit } from '@angular/core';
import {catchError, map, Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ClientsService} from "../../../clients/services/clients.service";
import {ClientDto} from "../../../../dtos/client";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {environment} from "../../../../../environments/environment";

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  apiLoaded: Observable<boolean>;

  options: google.maps.MapOptions = {
    center: {lat: -16.6159543, lng: -49.2942932},
    zoom: 13,
    streetViewControl: false,
    fullscreenControl: false,
    styles: [
      {
        featureType: "poi",
        elementType: "labels",
        stylers: [
          { visibility: "off" }
        ]
      }
    ]
  };

  clients: ClientDto[] = [];
  filteredClients: ClientDto[] = [];

  usersFilter: FormGroup;

  constructor(
    private httpClient: HttpClient,
    private clientHttpService: ClientsService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.apiLoaded = httpClient.jsonp(`https://maps.googleapis.com/maps/api/js?key=${environment.mapsKey}`, 'callback')
      .pipe(map(() => true), catchError(() => of(false)));

    this.usersFilter = this.formBuilder.group({
      selectedClients: [[]],
    });
  }

  ngOnInit(): void {
    this.clientHttpService.listarClientes().subscribe(
      (clients) => {
        this.clients = [...clients];
        this.filteredClients = [...clients];
      }
    );
  }

  handleChangeSelectValues() {
    const selectedClientsFormControl = this.usersFilter.value.selectedClients;
    if (selectedClientsFormControl.length === 0) {
      this.filteredClients = [...this.clients];
    } else {
      this.filteredClients = [...selectedClientsFormControl];
    }
  }

  handleCreateClientThisLocation(event: google.maps.MapMouseEvent): void {
    this.router.navigate(['clients/create'], {
      queryParams: { latitude: event.latLng?.toJSON().lat, longitude: event.latLng?.toJSON().lng }
    });
  }

}
