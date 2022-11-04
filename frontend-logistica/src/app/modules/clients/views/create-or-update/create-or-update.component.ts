import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientDto} from "../../../../dtos/client";
import {AddressDto} from "../../../../dtos/address";
import {ClientsService} from "../../services/clients.service";
import {ClientResolver} from "../../resolvers/client.resolver";

@Component({
  selector: 'app-create-or-update',
  templateUrl: './create-or-update.component.html',
  styleUrls: ['./create-or-update.component.css']
})
export class CreateOrUpdateComponent implements OnInit {

  client: ClientDto = <ClientDto> {name: '', cnpj: '', address: { latitude: 0, longitude: 0 }};
  formClient: FormGroup;
  isUpdate = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private clientHttpService: ClientsService,
    private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    if (this.activatedRoute.snapshot.data['client']) {
      this.isUpdate = true;
      this.client = this.activatedRoute.snapshot.data['client'];
    } else {
      this.client.address.latitude = Number(this.activatedRoute.snapshot.queryParamMap.get('latitude')) || 0;
      this.client.address.longitude = Number(this.activatedRoute.snapshot.queryParamMap.get('longitude')) || 0;
    }

    this.prepareForm();
  }

  createOrUpdateClient(): void {
    const formValues = this.formClient.value;
    const clientForm = {} as ClientDto;
    clientForm.address = {} as AddressDto;

    clientForm.name = formValues.nome;
    clientForm.cnpj = formValues.cnpj;
    clientForm.address.latitude = formValues.latitude;
    clientForm.address.longitude = formValues.longitude;
    clientForm.address.street = formValues.street;
    clientForm.address.number = formValues.number;
    clientForm.address.complement = formValues.complement;
    clientForm.address.neighborhood = formValues.neighborhood;
    clientForm.address.state = formValues.state;

    if (this.isUpdate) {
      this.updateClient(this.activatedRoute.snapshot.data['client'].id, clientForm);
    } else {
      this.createClient(clientForm);
    }



  }

  createClient(clientForm: ClientDto): void {
    this.clientHttpService.criarClienteNovo(clientForm).subscribe(
      (_) => this.router.navigate(['clients']),
      (error) => {
        console.log('MOSTRAR A SNACKBAR AQUI');
      }
    );
  }

  updateClient(clientId: number, clientForm: ClientDto): void {
    this.clientHttpService.atualizarCliente(clientId, clientForm).subscribe(
      (_) => this.router.navigate(['clients']),
      (error) => {
        console.log('MOSTRAR A SNACKBAR AQUI');
      }
    );
  }

  getSubmitButtonText() {
    return this.isUpdate ? 'ATUALIZAR' : 'CADASTRAR';
  }

  private prepareForm(): void {
    this.formClient = this.formBuilder.group({
      nome: [this.client.name, Validators.required],
      cnpj: [this.client.cnpj, Validators.required],
      latitude: [this.client.address.latitude, Validators.required],
      longitude: [this.client.address.longitude, Validators.required],
      street: [this.client.address.street, Validators.required],
      neighborhood: [this.client.address.neighborhood, Validators.required],
      state: [this.client.address.state, Validators.required],
      complement: [this.client.address.complement],
      number: [this.client.address.number],
    });
  }
}
