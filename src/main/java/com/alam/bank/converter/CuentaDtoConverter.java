package com.alam.bank.converter;

import com.alam.bank.entity.Cuenta;
import com.alam.bank.models.CuentaDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CuentaDtoConverter implements DtoConverter<Cuenta, CuentaDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cuenta fromDto(CuentaDto dto) {
        return this.modelMapper.map(dto, Cuenta.class);
    }

    @Override
    public List<Cuenta> fromDtoList(List<CuentaDto> dtoList) {
        return dtoList.stream()
                .map(d -> this.fromDto(d))
                .collect(Collectors.toList());
    }

    @Override
    public CuentaDto fromEntity(Cuenta entity) {
        CuentaDto cuentaDto =  this.modelMapper.map(entity, CuentaDto.class);
        cuentaDto.setTipoCuenta(entity.getTipoCuenta().getNombre());

        return cuentaDto;
    }

    @Override
    public List<CuentaDto> fromEntityList(List<Cuenta> entityList) {
        return entityList.stream()
                .map(e -> this.fromEntity(e))
                .collect(Collectors.toList());
    }

    @Override
    public void merge(CuentaDto dto, Cuenta entity) {
        this.modelMapper.map(dto, entity);
    }
}
