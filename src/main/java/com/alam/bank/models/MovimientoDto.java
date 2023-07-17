package com.alam.bank.models;

import com.alam.bank.entity.Cuenta;
import com.alam.bank.entity.TipoMovimiento;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto {
    private int id;

    private TipoMovimiento tipoMovimiento;

    private CuentaDto cuenta;

    private Double saldoInicial;

    private Double saldoDisponible;

    private Double valor;

    private Date fecha;
}
