package com.alam.bank.controller;

import com.alam.bank.models.CuentaDto;
import com.alam.bank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuentas")
public class CuentaController extends BaseController<CuentaDto, CuentaService>{

    @Autowired
    public CuentaController(CuentaService service){
        super(service);
    }
}
