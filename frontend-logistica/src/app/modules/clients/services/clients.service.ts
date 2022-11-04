import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {ClientDto} from "../../../dtos/client";
import {environment} from "../../../../environments/environment";
import {catchError, map, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private http: HttpClient) {}

  listarClientes(): Observable<ClientDto[]> {
    return this.http.get<ClientDto[]>(`${environment.backendUrl}/client`).pipe(
      map((obj) => obj),
      catchError(capturaErro)
    )
  }

  buscarClientePeloId(id: number): Observable<ClientDto> {
    return this.http.get<ClientDto>(`${environment.backendUrl}/client/${id}`).pipe(
      map((obj) => obj),
      catchError(capturaErro)
    );
  }

  criarClienteNovo(form: ClientDto): Observable<ClientDto> {
    return this.http.post<ClientDto>(`${environment.backendUrl}/client`, form).pipe(
      map((obj) => obj),
      catchError(capturaErro)
    );
  }

  atualizarCliente(id: number, form: ClientDto): Observable<ClientDto> {
    return this.http.put<ClientDto>(`${environment.backendUrl}/client/${id}`, form).pipe(
      map((obj) => obj),
      catchError(capturaErro)
    );
  }

  deletarCliente(id: number): Observable<void> {
    return this.http.delete<void>(`${environment.backendUrl}/client/${id}`, ).pipe(
      map((obj) => obj),
      catchError(capturaErro)
    );
  }
}


function capturaErro (err: HttpErrorResponse) {
  const erroBackEnd = JSON.stringify(err.error);
  const obj = JSON.parse(erroBackEnd);
  return throwError(obj.errors || err?.error?.message || 'Erro desconhecido, informe ao programador');
}
