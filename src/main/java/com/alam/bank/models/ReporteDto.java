package com.alam.bank.models;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDto {

    private Date fecha;

    private String cliente;

    private String numeroCuenta;

    private String tipo;

    private Double saldoInicial;

    private boolean estado;

    private Double movimiento;

    private Double saldoDisponible;

}
