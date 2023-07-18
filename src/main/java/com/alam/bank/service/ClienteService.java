package com.alam.bank.service;

import com.alam.bank.converter.ClienteDtoConverter;
import com.alam.bank.entity.Cliente;
import com.alam.bank.models.ClienteDto;
import com.alam.bank.repository.interfaces.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;

@Service
public class ClienteService extends BaseServiceImpl<ClienteDto, Cliente, ClienteRepository>{

    @Autowired
    public ClienteService(ClienteRepository repository,
                          ClienteDtoConverter clienteDtoConverter){
        super(repository, clienteDtoConverter);
    }

    public ClienteDto findByIdentificacion(String identificacion){
        Cliente cliente = this.repository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        return this.dtoConverter.fromEntity(cliente);
    }

    @Override
    public void delete(int id) {
        ClienteDto cliente = this.getById(id);
        cliente.setEstado(false);

        super.update(id, cliente);
    }
}
