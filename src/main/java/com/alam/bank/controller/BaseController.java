package com.alam.bank.controller;

import com.alam.bank.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController <T, R extends BaseService<T>>{

    protected R service;

    public BaseController(R service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable int id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<T> create(@RequestBody T model) {
        return new ResponseEntity<>(service.create(model), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable int id, @RequestBody T model) {
        return new ResponseEntity<>(service.update(id, model), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
