package com.alam.bank.repository.interfaces;

import com.alam.bank.entity.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Integer> {

    Optional<TipoCuenta> findByNombre(String nombre);

}
