package com.alam.bank.service;

import com.alam.bank.entity.Cuenta;
import com.alam.bank.entity.Movimiento;
import com.alam.bank.entity.TipoMovimiento;
import com.alam.bank.models.MovimientoRequestDTO;
import com.alam.bank.models.MovimientoResponseDTO;
import com.alam.bank.repository.interfaces.MovimientoRepository;
import com.alam.bank.repository.interfaces.TipoMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Repository
public class MovimientoService extends BaseServiceImpl<Movimiento, MovimientoRepository> {

    @Value("${limite.retiro.diario}")
    private int limiteRetiroDiario;

    private CuentaService cuentaService;

    private TipoMovimientoRepository tipoMovimientoRepository;

    @Autowired
    public MovimientoService(MovimientoRepository repository,
                             TipoMovimientoRepository tipoMovimientoRepository,
                             CuentaService cuentaService){
        super(repository);
        this.cuentaService = cuentaService;
        this.tipoMovimientoRepository = tipoMovimientoRepository;
    }

    public MovimientoResponseDTO debito(MovimientoRequestDTO movimientoRequestDTO){
        if(movimientoRequestDTO.getIdCuenta() == null || movimientoRequestDTO.getMonto() <= 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Cuenta cuenta = cuentaService.getById(movimientoRequestDTO.getIdCuenta());

        if(cuenta.getSaldo() < movimientoRequestDTO.getMonto())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo no disponible");

        TipoMovimiento tipoMovimiento = this.tipoMovimientoRepository.findByNombre("DEBITO");

        //Actualizando saldo
        double saldoInicial = cuenta.getSaldo();
        cuenta.debito(movimientoRequestDTO.getMonto());

        //Armando movimiento
        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(tipoMovimiento)
                .valor(movimientoRequestDTO.getMonto())
                .saldoInicial(saldoInicial)
                .saldoDisponible(cuenta.getSaldo())
                .fecha(new Date())
                .cuenta(cuenta)
                .build();

        //Guardando movimiento
        movimiento = this.repository.save(movimiento);

        //Actualizando cuenta
        cuentaService.update(cuenta.getNumeroCuenta(), cuenta);

        return convertToDTO(movimiento);
    }

    public MovimientoResponseDTO credito(MovimientoRequestDTO movimientoRequestDTO){
        if(movimientoRequestDTO.getIdCuenta() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        Cuenta cuenta = cuentaService.getById(movimientoRequestDTO.getIdCuenta());

        TipoMovimiento tipoMovimiento = this.tipoMovimientoRepository.findByNombre("CREDITO");

        //Actualizando saldo
        double saldoInicial = cuenta.getSaldo();
        cuenta.credito(movimientoRequestDTO.getMonto());

        //Armando movimiento
        Movimiento movimiento = Movimiento.builder()
                .tipoMovimiento(tipoMovimiento)
                .valor(movimientoRequestDTO.getMonto())
                .saldoInicial(saldoInicial)
                .saldoDisponible(cuenta.getSaldo())
                .fecha(new Date())
                .cuenta(cuenta)
                .build();

        //Guardando movimiento
        movimiento = this.repository.save(movimiento);

        //Actualizando cuenta
        cuentaService.update(cuenta.getNumeroCuenta(), cuenta);

        return convertToDTO(movimiento);
    }

    public MovimientoResponseDTO convertToDTO(Movimiento movimiento) {

        MovimientoResponseDTO dto = MovimientoResponseDTO.builder()
                .fecha(movimiento.getFecha())
                .cliente(movimiento.getCuenta().getCliente().getNombre())
                .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .tipo(movimiento.getCuenta().getTipoCuenta().getNombre())
                .estado(movimiento.getCuenta().isEstado())
                .saldoInicial(movimiento.getSaldoInicial())
                .movimiento(movimiento.getValor())
                .saldoDisponible(movimiento.getCuenta().getSaldo())
                .build();

        return dto;
    }

}
