package com.maxima.backendlogistica.api.dto;

import com.maxima.backendlogistica.commons.BaseEntityDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ClientDto extends BaseEntityDto {

    @NotNull
    private String name;

    @NotNull
    private String cnpj;

    @NotNull
    private AddressDto address;
}
