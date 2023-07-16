package com.alam.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "saldo")
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoCuenta tipoCuenta;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "cuenta")
    @JsonIgnore
    private List<Movimiento> movimientos;

    @Column(name = "estado")
    private boolean estado;

    public void debito(Double monto){
        if(this.saldo >= monto) {
            this.saldo -= monto;
        }
    }

    public void credito(Double monto){
        this.saldo += monto;
    }

}
