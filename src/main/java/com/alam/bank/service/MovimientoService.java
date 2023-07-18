package com.alam.bank.service;

import com.alam.bank.converter.MovimientoDtoConverter;
import com.alam.bank.entity.Movimiento;
import com.alam.bank.entity.TipoMovimiento;
import com.alam.bank.models.CuentaDto;
import com.alam.bank.models.MovimientoDto;
import com.alam.bank.models.MovimientoRequestDTO;
import com.alam.bank.repository.interfaces.MovimientoRepository;
import com.alam.bank.repository.interfaces.TipoMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class MovimientoService extends BaseServiceImpl<MovimientoDto, Movimiento, MovimientoRepository> {

    @Value("${limite.retiro.diario}")
    private int limiteRetiroDiario;

    private CuentaService cuentaService;

    private TipoMovimientoRepository tipoMovimientoRepository;

    private static final String CREDITO = "CREDITO";
    private static final String DEBITO = "DEBITO";


    @Autowired
    public MovimientoService(MovimientoRepository repository,
                             MovimientoDtoConverter movimientoDtoConverter,
                             TipoMovimientoRepository tipoMovimientoRepository,
                             CuentaService cuentaService){
        super(repository, movimientoDtoConverter);
        this.cuentaService = cuentaService;
        this.tipoMovimientoRepository = tipoMovimientoRepository;
    }

    public MovimientoDto debito(MovimientoRequestDTO movimientoRequestDTO){
        if(movimientoRequestDTO.getIdCuenta() == null || movimientoRequestDTO.getMonto() == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        CuentaDto cuenta = cuentaService.getById(movimientoRequestDTO.getIdCuenta());

        if(cuenta.getSaldo() < movimientoRequestDTO.getMonto())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo no disponible");

        if(this.sobrepasaLimiteDeRetiro(cuenta))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cupo diario Excedido");

        TipoMovimiento tipoMovimiento = this.tipoMovimientoRepository.findByNombre(DEBITO);

        //Actualizando saldo
        double saldoInicial = cuenta.getSaldo();
        if(saldoInicial >= movimientoRequestDTO.getMonto()) {
            cuenta.setSaldo(cuenta.getSaldo() - movimientoRequestDTO.getMonto());
        }

        //Armando movimiento
        if(movimientoRequestDTO.getMonto() > 0)
            movimientoRequestDTO.setMonto(movimientoRequestDTO.getMonto() * -1);

        MovimientoDto movimiento = MovimientoDto.builder()
                .tipoMovimiento(tipoMovimiento)
                .valor(movimientoRequestDTO.getMonto())
                .saldoInicial(saldoInicial)
                .saldoDisponible(cuenta.getSaldo())
                .fecha(new Date())
                .cuenta(cuenta)
                .build();

        //Guardando movimiento
        movimiento = this.create(movimiento);

        //Actualizando cuenta
        cuentaService.update(cuenta.getNumeroCuenta(), cuenta);

        return movimiento;
    }

    public MovimientoDto credito(MovimientoRequestDTO movimientoRequestDTO){
        if(movimientoRequestDTO.getIdCuenta() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        CuentaDto cuenta = cuentaService.getById(movimientoRequestDTO.getIdCuenta());

        TipoMovimiento tipoMovimiento = this.tipoMovimientoRepository.findByNombre(CREDITO);

        //Actualizando saldo
        double saldoInicial = cuenta.getSaldo();
        cuenta.setSaldo(cuenta.getSaldo() + movimientoRequestDTO.getMonto());

        //Armando movimiento
        MovimientoDto movimiento = MovimientoDto.builder()
                .tipoMovimiento(tipoMovimiento)
                .valor(movimientoRequestDTO.getMonto())
                .saldoInicial(saldoInicial)
                .saldoDisponible(cuenta.getSaldo())
                .fecha(new Date())
                .cuenta(cuenta)
                .build();

        //Guardando movimiento
        movimiento = this.create(movimiento);

        //Actualizando cuenta
        cuentaService.update(cuenta.getNumeroCuenta(), cuenta);

        return movimiento;
    }

    public boolean sobrepasaLimiteDeRetiro(CuentaDto cuenta) {

        List<Movimiento> movimientoList = this.repository.findBycuentaIdFecha(cuenta.getNumeroCuenta(), new Date());

        if(movimientoList.isEmpty())
            return false;

        Double montoTotal = movimientoList.stream()
                .filter(m -> m.getTipoMovimiento().getNombre().equals(DEBITO))
                .map(m -> m.getValor())
                .reduce(0d, Double::sum);

        return (montoTotal * -1) >= limiteRetiroDiario;
    }

}
