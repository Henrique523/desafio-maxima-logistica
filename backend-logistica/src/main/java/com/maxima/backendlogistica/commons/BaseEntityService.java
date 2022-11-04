package com.maxima.backendlogistica.commons;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public abstract class BaseEntityService<T> {

    @Autowired
    protected BaseEntityRepository<T, Long> repository;

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public T save(T entity) {
        return this.repository.save(entity);
    }

    public T searchById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException((getClass().getSimpleName() +
                        " not found with Id " + id)));
    }

    public List<T> findAll() {
        return this.repository.findAllByDeletadoEmNull();
    }

    public T update(Long id, T entity) {
        T databaseEntity = this.searchById(id);

        BeanUtils.copyProperties(entity, databaseEntity, "id", "criadoEm", "atualizadoEm", "deletadoEm");

        return this.save(databaseEntity);
    }

    public void delete(Long id) {
        T databaseEntity = this.searchById(id);

        ((BaseEntity) databaseEntity).setDeletadoEm(LocalDateTime.now());

        this.save(databaseEntity);
    }

}
