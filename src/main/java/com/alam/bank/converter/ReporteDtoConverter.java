package com.alam.bank.converter;

import com.alam.bank.entity.Movimiento;
import com.alam.bank.models.ReporteDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ReporteDtoConverter {

    public ReporteDto fromMovimientoEntity(Movimiento movimiento){
        return ReporteDto.builder()
                .fecha(movimiento.getFecha())
                .cliente(movimiento.getCuenta().getCliente().getNombre())
                .numeroCuenta(String.valueOf(movimiento.getCuenta().getNumeroCuenta()))
                .tipo(movimiento.getCuenta().getTipoCuenta().getNombre())
                .saldoInicial(movimiento.getSaldoInicial())
                .estado(movimiento.getCuenta().isEstado())
                .movimiento(movimiento.getValor())
                .saldoDisponible(movimiento.getSaldoDisponible())
                .build();
    }

    public List<ReporteDto> fromMovimientoEntityList(List<Movimiento> movimientos){
        return movimientos.stream()
                .map(m -> this.fromMovimientoEntity(m))
                .collect(Collectors.toList());

    }

}
