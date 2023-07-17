package com.alam.bank.converter;

import java.util.List;

public interface DtoConverter<T,U>{
    T fromDto(U dto);

    List<T> fromDtoList(List<U> dtoList);

    U fromEntity(T entity);

    List<U> fromEntityList(List<T> entityList);

    void merge(U dto, T entity);

}
