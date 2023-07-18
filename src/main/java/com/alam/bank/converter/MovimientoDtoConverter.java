package com.alam.bank.converter;

import com.alam.bank.entity.Movimiento;
import com.alam.bank.models.MovimientoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class MovimientoDtoConverter implements DtoConverter<Movimiento, MovimientoDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Movimiento fromDto(MovimientoDto dto) {
        return this.modelMapper.map(dto, Movimiento.class);
    }

    @Override
    public List<Movimiento> fromDtoList(List<MovimientoDto> dtoList) {
        return dtoList.stream()
                .map(d -> this.modelMapper.map(d, Movimiento.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovimientoDto fromEntity(Movimiento entity) {
        MovimientoDto movimientoDto = this.modelMapper.map(entity, MovimientoDto.class);
        movimientoDto.setTipoMovimiento(entity.getTipoMovimiento().getNombre());

        return movimientoDto;
    }

    @Override
    public List<MovimientoDto> fromEntityList(List<Movimiento> entityList) {
        return entityList.stream()
                .map(e -> this.fromEntity(e))
                .collect(Collectors.toList());
    }

    @Override
    public void merge(MovimientoDto dto, Movimiento entity) {
        this.modelMapper.map(dto, entity);
    }
}
