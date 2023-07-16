package com.alam.bank.controller;


import com.alam.bank.entity.Cliente;
import com.alam.bank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/cliente")
public class ClienteController extends BaseController<Cliente, ClienteService> {

    @Autowired
    public ClienteController(ClienteService clienteService) {
        super(clienteService);
    }

}
