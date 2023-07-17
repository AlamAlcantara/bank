package com.alam.bank.service;

import com.alam.bank.converter.DtoConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * T = DTO
 * U = Entidad
 * R = Repo
 * */
public abstract class BaseServiceImpl<T, U, R extends JpaRepository<U, Integer>> implements BaseService<T>{

    protected R repository;

    protected DtoConverter<U,T> dtoConverter;

    public BaseServiceImpl(R repository, DtoConverter<U,T> dtoConverter){
        this.repository = repository;
        this.dtoConverter = dtoConverter;
    }

    public T create(T model) {
        U entity = dtoConverter.fromDto(model);
        repository.save(entity);
        return model;
    }

    public List<T> getAll() {
        return dtoConverter.fromEntityList(repository.findAll());
    }

    public T getById(int id) {
         U entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado"));
         return dtoConverter.fromEntity(entity);
    }

    public T update(int id, T model) {
        U entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado"));
        dtoConverter.merge(model, entity);
        entity = repository.save(entity);
        return dtoConverter.fromEntity(entity);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
