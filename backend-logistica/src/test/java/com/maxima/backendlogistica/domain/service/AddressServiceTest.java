package com.maxima.backendlogistica.domain.service;

import com.maxima.backendlogistica.ApplicationConfigTest;
import com.maxima.backendlogistica.domain.model.Address;
import com.maxima.backendlogistica.domain.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@DisplayName("AddressServiceTest")
public class AddressServiceTest extends ApplicationConfigTest {

    @MockBean
    protected AddressRepository repository;

    @Autowired
    private AddressService addressService;

    @Test
    @DisplayName("Deve salvar um endereço quando os dados estão corretos")
    public void should_register_correct_address_when_correct_data_is_passed() {
        Optional<Address> address = Optional.of(this.createAddress());
        Mockito.when(repository.save(ArgumentMatchers.eq(address.get()))).thenReturn(address.get());

        this.addressService.save(address.get());

        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(Address.class));
    }

    @Test
    @DisplayName("Deve atualizar um endereço corretamente")
    public void should_update_address_info_correctly() {
        Optional<Address> address = Optional.of(this.createAddress());

        Mockito.when(repository.findById(ArgumentMatchers.eq(address.get().getId()))).thenReturn(address);
        Mockito.when(repository.save(ArgumentMatchers.eq(address.get()))).thenReturn(address.get());

        this.addressService.update(address.get().getId(), address.get());

        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(Address.class));
    }

    @Test
    @DisplayName("Deve retornar uma exceção quando atualizar um endereço com ID inválido")
    public void should_throws_an_error_when_update_with_incorrect_address_id() {
        Optional<Address> address = Optional.of(this.createIncorrectAddress());

        Mockito.when(repository.findById(ArgumentMatchers.eq(address.get().getId()))).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.addressService.update(address.get().getId(), address.get()),
                Address.class.getSimpleName() + " not found with Id " + address.get().getId());
    }

    @Test
    @DisplayName("Deve deletar um endereço corretamente")
    public void should_delete_address_correctly() {
        Long addressId = 1L;

        Optional<Address> address = Optional.of(this.createAddress());
        Mockito.when(repository.findById(ArgumentMatchers.eq(addressId))).thenReturn(address);

        this.addressService.delete(address.get().getId());

        Mockito.verify(repository, Mockito.times(1)).findById(address.get().getId());
        Mockito.verify(repository, Mockito.times(1)).save(address.get());
    }


    @Test
    @DisplayName("Deve retornar uma exceção quando deletar um endereço com ID inválido")
    public void should_throws_an_error_when_delete_with_incorrect_address_id() {
        Optional<Address> address = Optional.of(this.createIncorrectAddress());

        Mockito.when(repository.findById(ArgumentMatchers.eq(address.get().getId()))).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> this.addressService.delete(address.get().getId()),
                Address.class.getSimpleName() + " not found with Id " + address.get().getId());
    }

    private Address createAddress() {
        Address address = Mockito.mock(Address.class);
        Mockito.when(address.getId()).thenReturn(1L);
        Mockito.when(address.getLatitude()).thenReturn(-16.73050000f);
        Mockito.when(address.getLongitude()).thenReturn(-49.29920000f);
        return address;
    }

    private Address createIncorrectAddress() {
        Address address = Mockito.mock(Address.class);
        Mockito.when(address.getLatitude()).thenReturn(-16.73050000f);
        Mockito.when(address.getLongitude()).thenReturn(-49.29920000f);
        return address;
    }

}
