package com.maxima.backendlogistica.domain.service;

import com.maxima.backendlogistica.commons.BaseEntityService;
import com.maxima.backendlogistica.domain.model.Address;
import com.maxima.backendlogistica.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClientService extends BaseEntityService<Client> {

    @Autowired
    private AddressService addressService;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public Client save(Client client) {
        this.addressService.save(client.getAddress());

        return super.save(client);
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public Client update(Long id, Client client) {
        Client clientToUpdate = super.searchById(id);

        this.addressService.update(clientToUpdate.getAddress().getId(), client.getAddress());

        client.setAddress(clientToUpdate.getAddress());

        return super.update(id, client);
    }
}
