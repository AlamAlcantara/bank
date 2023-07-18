package com.alam.bank.service;

import com.alam.bank.converter.CuentaDtoConverter;
import com.alam.bank.entity.Cuenta;
import com.alam.bank.entity.TipoCuenta;
import com.alam.bank.models.CuentaDto;
import com.alam.bank.repository.interfaces.CuentaRepository;
import com.alam.bank.repository.interfaces.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CuentaService extends BaseServiceImpl<CuentaDto, Cuenta, CuentaRepository> {

    private ClienteService clienteService;

    private TipoCuentaRepository tipoCuentaRepository;

    @Autowired
    public CuentaService(CuentaRepository repository,
                         CuentaDtoConverter cuentaDtoConverter,
                         ClienteService clienteService,
                         TipoCuentaRepository tipoCuentaRepository) {
        super(repository, cuentaDtoConverter);
        this.clienteService = clienteService;
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    @Override
    public CuentaDto create(CuentaDto model) {
       Cuenta cuenta = this.fillNestedEntities(model);
       cuenta = this.repository.save(cuenta);

       return this.dtoConverter.fromEntity(cuenta);
    }

    @Override
    public CuentaDto update(int id, CuentaDto model) {
        return this.create(model);
    }

    @Override
    public void delete(int id) {
        CuentaDto cuentaDto = this.getById(id);
        cuentaDto.setEstado(false);

        super.update(id, cuentaDto);
    }

    public Cuenta fillNestedEntities(CuentaDto model) {
        model.setCliente(clienteService.findByIdentificacion(model.getCliente().getIdentificacion()));

        TipoCuenta tipoCuenta = this.tipoCuentaRepository.findByNombre(model.getTipoCuenta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de cuenta no encontrada"));

        Cuenta cuenta = this.dtoConverter.fromDto(model);
        cuenta.setTipoCuenta(tipoCuenta);

        return cuenta;
    }
}
