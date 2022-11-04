package com.maxima.backendlogistica.domain.service;

import com.maxima.backendlogistica.ApplicationConfigTest;
import com.maxima.backendlogistica.domain.model.Address;
import com.maxima.backendlogistica.domain.model.Client;
import com.maxima.backendlogistica.domain.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@DisplayName("ClientServiceTest")
public class ClientServiceTest extends ApplicationConfigTest {

    @MockBean
    private AddressService addressService;

    @MockBean
    protected ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Test
    @DisplayName("Deve salvar um cliente quando os dados estão corretos")
    public void should_register_correct_address_when_correct_data_is_passed() {
        Optional<Client> client = Optional.of(this.createClient());
        Mockito.when(clientRepository.save(ArgumentMatchers.eq(client.get()))).thenReturn(client.get());

        this.clientService.save(client.get());

        Mockito.verify(addressService, Mockito.times(1)).save(ArgumentMatchers.any(Address.class));
        Mockito.verify(clientRepository, Mockito.times(1)).save(ArgumentMatchers.any(Client.class));
    }

    @Test
    @DisplayName("Deve atualizar um cliente corretamente")
    public void should_update_client_info_correctly() {
        Optional<Client> client = Optional.of(this.createClient());

        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(client.get().getId()))).thenReturn(client);
        Mockito.when(clientRepository.save(ArgumentMatchers.eq(client.get()))).thenReturn(client.get());

        this.clientService.update(client.get().getId(), client.get());

        Mockito.verify(addressService, Mockito.times(1)).update(client.get().getAddress().getId(), client.get().getAddress());
        Mockito.verify(clientRepository, Mockito.times(2)).findById(client.get().getId());
        Mockito.verify(clientRepository, Mockito.times(1)).save(ArgumentMatchers.any(Client.class));
    }

    @Test
    @DisplayName("Deve retornar uma exceção quando atualizar um cliente com ID do endereço inválido")
    public void should_throws_an_error_when_update_with_incorrect_address_id() {
        Optional<Client> client = Optional.of(this.createIncorrectClient());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.clientService.update(client.get().getId(), client.get()),
                Address.class.getSimpleName() + " not found with Id " + client.get().getId());
    }

    @Test
    @DisplayName("Deve retornar uma exceção quando atualizar um cliente com ID inválido")
    public void should_throws_an_error_when_update_with_incorrect_client_id() {
        Optional<Client> client = Optional.of(this.createIncorrectClient());

        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(client.get().getId()))).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.clientService.update(client.get().getId(), client.get()),
                Client.class.getSimpleName() + " not found with Id " + client.get().getId());
    }

    @Test
    @DisplayName("Deve deletar um cliente corretamente")
    public void should_delete_client_correctly() {
        Long clientId = 1L;

        Optional<Client> client = Optional.of(this.createClient());
        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(clientId))).thenReturn(client);

        this.clientService.delete(client.get().getId());

        Mockito.verify(clientRepository, Mockito.times(1)).findById(client.get().getId());
        Mockito.verify(clientRepository, Mockito.times(1)).save(client.get());
    }

    @Test
    @DisplayName("Deve retornar uma exceção quando deletar um cliente com ID inválido")
    public void should_throws_an_error_when_delete_with_incorrect_client_id() {
        Optional<Client> client = Optional.of(this.createIncorrectClient());

        Mockito.when(clientRepository.findById(ArgumentMatchers.eq(client.get().getId()))).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.clientService.delete(client.get().getId()),
                Client.class.getSimpleName() + " not found with Id " + client.get().getId());
    }

    private Client createClient() {
        Client client = Mockito.mock(Client.class);
        Address address = Mockito.mock(Address.class);
        Mockito.when(client.getId()).thenReturn(1L);
        Mockito.when(client.getAddress()).thenReturn(address);

        return client;
    }

    private Client createIncorrectClient() {
        Client client = Mockito.mock(Client.class);
        Address address = Mockito.mock(Address.class);
        Mockito.when(client.getAddress()).thenReturn(address);
        Mockito.when(client.getAddress().getId()).thenReturn(null);
        return Mockito.mock(Client.class);
    }
}
