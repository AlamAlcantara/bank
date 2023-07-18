package com.alam.bank.converter;

import com.alam.bank.entity.TipoMovimiento;
import com.alam.bank.models.TipoMovimientoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TipoMovimientoDtoConverter implements DtoConverter<TipoMovimiento, TipoMovimientoDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TipoMovimiento fromDto(TipoMovimientoDto dto) {
        return this.modelMapper.map(dto, TipoMovimiento.class);
    }

    @Override
    public List<TipoMovimiento> fromDtoList(List<TipoMovimientoDto> dtoList) {
        return dtoList.stream()
                .map(d -> this.modelMapper.map(d, TipoMovimiento.class))
                .collect(Collectors.toList());
    }

    @Override
    public TipoMovimientoDto fromEntity(TipoMovimiento entity) {
        return this.modelMapper.map(entity, TipoMovimientoDto.class);
    }

    @Override
    public List<TipoMovimientoDto> fromEntityList(List<TipoMovimiento> entityList) {
        return entityList.stream()
                .map(e -> this.fromEntity(e))
                .collect(Collectors.toList());
    }

    @Override
    public void merge(TipoMovimientoDto dto, TipoMovimiento entity) {
        this.modelMapper.map(dto, entity);
    }
}
