package com.alam.bank.converter;

import com.alam.bank.entity.TipoCuenta;
import com.alam.bank.models.TipoCuentaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class TipoCuentaDtoConverter implements DtoConverter<TipoCuenta, TipoCuentaDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TipoCuenta fromDto(TipoCuentaDto dto) {
        return this.modelMapper.map(dto, TipoCuenta.class);
    }

    @Override
    public List<TipoCuenta> fromDtoList(List<TipoCuentaDto> dtoList) {
        return dtoList.stream()
                .map(d -> this.modelMapper.map(d, TipoCuenta.class))
                .collect(Collectors.toList());
    }

    @Override
    public TipoCuentaDto fromEntity(TipoCuenta entity) {
        return this.modelMapper.map(entity, TipoCuentaDto.class);
    }

    @Override
    public List<TipoCuentaDto> fromEntityList(List<TipoCuenta> entityList) {
        return entityList.stream()
                .map(e -> this.fromEntity(e))
                .collect(Collectors.toList());
    }

    @Override
    public void merge(TipoCuentaDto dto, TipoCuenta entity) {
        this.modelMapper.map(dto, entity);
    }
}
