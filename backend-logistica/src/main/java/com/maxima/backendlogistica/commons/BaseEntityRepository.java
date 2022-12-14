package com.maxima.backendlogistica.commons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseEntityRepository<T, ID> extends JpaRepository<T, ID> {
    List<T> findAllByDeletadoEmNull();
}
