package com.alam.bank.service;

import com.alam.bank.entity.Cliente;
import com.alam.bank.repository.interfaces.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseServiceImpl<Cliente, ClienteRepository>{

    @Autowired
    public ClienteService(ClienteRepository repository){
        super(repository);
    }


}
