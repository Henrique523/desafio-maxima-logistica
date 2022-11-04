package com.maxima.backendlogistica.domain.model;

import com.maxima.backendlogistica.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "logistica", name = "address")
public class Address extends BaseEntity {

    @Column
    private Float latitude;

    @Column
    private Float longitude;

    @Column
    private String street;

    @Column
    private Integer number;

    @Column
    private String complement;

    @Column
    private String neighborhood;

    @Column
    private String state;
}
