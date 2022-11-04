package com.maxima.backendlogistica.api.assembler;

import com.maxima.backendlogistica.api.dto.ClientDto;
import com.maxima.backendlogistica.commons.BaseEntityAssembler;
import com.maxima.backendlogistica.domain.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientAssembler extends BaseEntityAssembler<Client, ClientDto, ClientDto> {
}
