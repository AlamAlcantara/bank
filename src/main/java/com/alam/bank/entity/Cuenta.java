package com.alam.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
public class Cuenta {

    @Id
    @Column(name = "numeroCuenta")
    private int numeroCuenta;

    @Column(name = "saldo_inicial")
    private double saldoInicial;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoCuenta tipoCuenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private TipoCuenta cliente;

    @OneToMany(mappedBy = "cuenta")
    private List<Cuenta> movimientos;

    @Column(name = "estado")
    private boolean estado;

}
