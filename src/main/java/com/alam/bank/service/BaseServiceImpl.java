package com.alam.bank.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public abstract class BaseServiceImpl<T, R extends JpaRepository<T, Integer>> implements BaseService<T>{

    protected R repository;

    public BaseServiceImpl(R repository){
        this.repository = repository;
    }

    public T create(T model) {
        return repository.save(model);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Registro no encontrado"));
    }

    public T update(int id, T model) {
        return repository.save(model);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
