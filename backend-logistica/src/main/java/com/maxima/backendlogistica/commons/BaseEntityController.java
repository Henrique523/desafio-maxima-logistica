package com.maxima.backendlogistica.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class BaseEntityController<T, REQ, RES> {

    @Autowired
    private BaseEntityService<T> service;

    @Autowired
    private BaseEntityAssembler<T, REQ, RES> assembler;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RES save(@RequestBody @Valid REQ requestDto) {
        T entityToSave = this.assembler.toDomainObjectFromDto(requestDto);
        return this.assembler.toDtoFromModel(this.service.save(entityToSave));
    }

    @GetMapping
    public List<RES> list() {
        return this.assembler.toCollectionDtoFromModel(this.service.findAll());
    }

    @GetMapping("/{id}")
    public RES findById(@PathVariable Long id) {
        return this.assembler.toDtoFromModel(this.service.searchById(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RES update(@PathVariable Long id, @RequestBody @Valid  REQ requestDto) {
        T entityToSave = this.assembler.toDomainObjectFromDto(requestDto);
        return this.assembler.toDtoFromModel(this.service.update(id, entityToSave));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }
}
