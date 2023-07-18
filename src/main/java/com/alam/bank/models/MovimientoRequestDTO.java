package com.alam.bank.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoRequestDTO {
    private Integer idCuenta;
    private Double monto;
}
