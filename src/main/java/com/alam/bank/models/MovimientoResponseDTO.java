package com.alam.bank.models;


import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
