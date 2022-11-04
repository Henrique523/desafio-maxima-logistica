package com.maxima.backendlogistica.domain.model;

import com.maxima.backendlogistica.commons.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(schema = "logistica", name = "client")
public class Client extends BaseEntity {

    @Column(name = "nome")
    private String name;

    @Column
    private String cnpj;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
