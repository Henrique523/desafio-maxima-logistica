package com.maxima.backendlogistica.api.assembler;

import com.maxima.backendlogistica.api.dto.AddressDto;
import com.maxima.backendlogistica.commons.BaseEntityAssembler;
import com.maxima.backendlogistica.domain.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressAssembler extends BaseEntityAssembler<Address, AddressDto, AddressDto> {
}
