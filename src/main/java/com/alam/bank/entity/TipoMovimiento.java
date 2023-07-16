package com.alam.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_movimiento")
@Getter
@Setter
public class TipoMovimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(mappedBy = "tipoMovimiento")
    @JsonIgnore
    private List<Movimiento> movimientos;
}
