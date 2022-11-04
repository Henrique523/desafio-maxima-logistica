package com.maxima.backendlogistica.domain.repository;

import com.maxima.backendlogistica.commons.BaseEntityRepository;
import com.maxima.backendlogistica.domain.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends BaseEntityRepository<Address, Long> {
}
