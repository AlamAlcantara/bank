package com.alam.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente extends Persona{

    @Column(name = "constraseña")
    private String contrasena;

    @Column(name = "estado")
    private boolean estado;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Cuenta> cuentas;

    public long getEdad() {
        return ChronoUnit.YEARS.between(this.getFechaNacimiento()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate(), LocalDate.now());
    }

}
