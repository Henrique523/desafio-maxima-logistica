import {AddressDto} from "./address";

export interface ClientDto {
  id: number;
  name: string;
  cnpj: string;
  address: AddressDto;
}
