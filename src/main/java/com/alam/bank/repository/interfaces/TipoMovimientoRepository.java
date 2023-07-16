package com.alam.bank.repository.interfaces;

import com.alam.bank.entity.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {

    @Query("SELECT m FROM TipoMovimiento m WHERE m.nombre = ?1")
    TipoMovimiento findByNombre(String nombre);

}
