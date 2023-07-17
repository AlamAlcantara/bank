package com.alam.bank.service;

import com.alam.bank.converter.ClienteDtoConverter;
import com.alam.bank.entity.Cliente;
import com.alam.bank.models.ClienteDto;
import com.alam.bank.repository.interfaces.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseServiceImpl<ClienteDto, Cliente, ClienteRepository>{

    @Autowired
    public ClienteService(ClienteRepository repository,
                          ClienteDtoConverter clienteDtoConverter){
        super(repository, clienteDtoConverter);
    }


}
