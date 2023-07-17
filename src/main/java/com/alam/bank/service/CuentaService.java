package com.alam.bank.service;

import com.alam.bank.converter.CuentaDtoConverter;
import com.alam.bank.entity.Cuenta;
import com.alam.bank.models.CuentaDto;
import com.alam.bank.repository.interfaces.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CuentaService extends BaseServiceImpl<CuentaDto, Cuenta, CuentaRepository> {

    @Autowired
    public CuentaService(CuentaRepository repository, CuentaDtoConverter cuentaDtoConverter) {
        super(repository, cuentaDtoConverter);
    }

}
