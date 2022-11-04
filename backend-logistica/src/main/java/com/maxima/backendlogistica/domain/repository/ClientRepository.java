package com.maxima.backendlogistica.domain.repository;

import com.maxima.backendlogistica.commons.BaseEntityRepository;
import com.maxima.backendlogistica.domain.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends BaseEntityRepository<Client, Long> {
}
