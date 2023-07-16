package com.alam.bank.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoRequestDTO {
    private Integer idCuenta;
    private Double monto;
}
