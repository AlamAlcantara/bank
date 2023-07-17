package com.alam.bank.converter;

import com.alam.bank.entity.Cliente;
import com.alam.bank.models.ClienteDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteDtoConverter implements DtoConverter<Cliente, ClienteDto>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cliente fromDto(ClienteDto dto) {
        return this.modelMapper.map(dto, Cliente.class);
    }

    @Override
    public List<Cliente> fromDtoList(List<ClienteDto> dtoList) {
        return dtoList.stream()
                .map(d -> this.modelMapper.map(d, Cliente.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto fromEntity(Cliente entity) {
        return this.modelMapper.map(entity, ClienteDto.class);
    }

    @Override
    public List<ClienteDto> fromEntityList(List<Cliente> entityList) {
        return entityList.stream()
                .map(e -> this.fromEntity(e))
                .collect(Collectors.toList());
    }

    @Override
    public void merge(ClienteDto dto, Cliente entity) {
        this.modelMapper.map(dto, entity);
    }
}
