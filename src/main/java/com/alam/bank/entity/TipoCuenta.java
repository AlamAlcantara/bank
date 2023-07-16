package com.alam.bank.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_cuenta")
@Getter
@Setter
public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "tipoCuenta")
    @JsonIgnore
    private List<Cuenta> cuentas;
}
