package com.alam.bank.controller;

import com.alam.bank.entity.Cuenta;
import com.alam.bank.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuentas")
public class CuentaController extends BaseController<Cuenta, CuentaService>{

    @Autowired
    public CuentaController(CuentaService service){
        super(service);
    }
}
