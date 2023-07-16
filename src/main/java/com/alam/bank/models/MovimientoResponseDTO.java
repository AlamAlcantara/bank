package com.alam.bank.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class MovimientoResponseDTO {

    private Date fecha;

    private String cliente;

    private int numeroCuenta;

    //tipo de cuenta
    private String tipo;

    private boolean estado;

    private Double saldoInicial;

    private Double movimiento;

    private Double saldoDisponible;

}
