package com.maxima.backendlogistica.commons;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public abstract class BaseEntityAssembler<T, REQ, RES> {
    private final Class<T> entityClass;
    private final Class<REQ> requestClass;
    private final Class<RES> responseClass;

    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    @Autowired
    private ModelMapper mapper;

    @SuppressWarnings("unchecked")
    public BaseEntityAssembler() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.requestClass = (Class<REQ>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        this.responseClass = (Class<RES>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }

    public RES toDtoFromModel(T entityClass) {
        return mapper.map(entityClass, getResponseClass());
    }

    public List<RES> toCollectionDtoFromModel(Collection<T> requestEntities) {
        return requestEntities.stream()
                .map(this::toDtoFromModel)
                .collect(Collectors.toList());
    }

    public List<T> toCollectionModelFromCollectionDto(Collection<REQ> requestEntities) {
        return requestEntities.stream()
                .map(this::toDomainObjectFromDto)
                .collect(Collectors.toList());
    }


    public T toDomainObjectFromDto(REQ requestClass) {
        return mapper.map(requestClass, getEntityClass());
    }

    public void copyToDomainObjectFromDto(REQ requestClass, T entityClass) {
        mapper.map(requestClass, entityClass);
    }
}
