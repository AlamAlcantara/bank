package com.alam.bank.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TipoCuentaDto {
    private Integer id;
    private String nombre;
}
