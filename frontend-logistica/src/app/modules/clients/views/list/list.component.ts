import {Component, OnInit} from '@angular/core';
import {ClientDto} from "../../../../dtos/client";
import {ClientsService} from "../../services/clients.service";
import {PageEvent} from "@angular/material/paginator";
import {Router} from "@angular/router";

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  displayedColumns: string[] = ['name', 'cnpj', 'code', 'latitude', 'longitude', 'actions'];
  dataSource: ClientDto[] = [];
  size = 5;
  totalElements = 0;

  constructor(private clientHttpService: ClientsService, private router: Router) {}

  ngOnInit(): void {
    this.clientHttpService.listarClientes().subscribe(
      (clients) => {
        this.dataSource = [...clients];

        // this.size = Math.floor(clients.length / this.size);
        // this.totalElements = Math.floor(clients.length / this.size);
      },
      (error) => console.log(error, 'ERROR'),
    )
  }

  page(event: PageEvent) {
    console.log(event);
  }

  handleDeleteClient(id: number): void {
    let result = window.confirm(`Tem certeza que deseja deletar o Cliente de ID '${id}'?`);

    if (!result) {
      return;
    }

    this.clientHttpService.deletarCliente(id).subscribe(
      () => location.reload(),
      (error) => console.log(error, 'ERROR')
    )
  }

  handleGoToUpdateClient(id: number): void {
    this.router.navigate([`clients/update/${id}`])
  }
}
