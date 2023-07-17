package com.alam.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {

    private Integer id;

    private String identificacion;

    private String nombre;

    private String genero;

    private String direccion;

    private String telefono;

    private Date fechaNacimiento;

    private long edad;

    private boolean estado;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

}
