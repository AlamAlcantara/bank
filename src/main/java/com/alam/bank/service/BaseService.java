package com.alam.bank.service;

import java.util.List;

public interface BaseService<T> {

    T create(T model);
    List<T> getAll();
    T getById(int id);
    T update(int id, T model);
    void delete(int id);



}
