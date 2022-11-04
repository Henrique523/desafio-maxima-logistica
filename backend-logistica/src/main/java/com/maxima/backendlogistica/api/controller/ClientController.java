package com.maxima.backendlogistica.api.controller;

import com.maxima.backendlogistica.api.dto.ClientDto;
import com.maxima.backendlogistica.commons.BaseEntityController;
import com.maxima.backendlogistica.domain.model.Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController extends BaseEntityController<Client, ClientDto, ClientDto> {
}
