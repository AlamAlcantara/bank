package com.alam.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento")
    private TipoCuenta tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private TipoCuenta cuenta;

    @Column(name = "saldo")
    private double saldo;

    @Column(name = "valor")
    private double valor;

    @Column(name = "fecha")
    private Date fecha;
}
