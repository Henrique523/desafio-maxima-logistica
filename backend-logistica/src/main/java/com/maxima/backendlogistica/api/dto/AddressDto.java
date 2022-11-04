package com.maxima.backendlogistica.api.dto;

import com.maxima.backendlogistica.commons.BaseEntityDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto extends BaseEntityDto {

    @NotNull
    private Float latitude;

    @NotNull
    private Float longitude;

    @NotNull
    private String street;

    @NotNull
    private String neighborhood;

    @NotNull
    private String state;

    private Integer number;
    private String complement;
}
