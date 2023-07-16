package com.alam.bank.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimiento")
    private TipoMovimiento tipoMovimiento;

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta cuenta;

    @Column(name = "saldo_inicial")
    private Double saldoInicial;

    @Column(name = "saldo_disponible")
    private Double saldoDisponible;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "fecha")
    private Date fecha;
}
