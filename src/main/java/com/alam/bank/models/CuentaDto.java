package com.alam.bank.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {

    private int numeroCuenta;

    private String tipoCuenta;

    private double saldo;

    private boolean estado;

    private ClienteDto cliente;

}
