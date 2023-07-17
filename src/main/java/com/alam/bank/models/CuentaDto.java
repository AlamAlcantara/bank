package com.alam.bank.models;

import com.alam.bank.entity.Cliente;
import com.alam.bank.entity.TipoCuenta;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {

    private int numeroCuenta;

    private TipoCuenta tipoCuenta;

    private double saldo;

    private boolean estado;

    private ClienteDto cliente;

}
