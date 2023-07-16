package com.alam.bank.repository.interfaces;

import com.alam.bank.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

    @Query(value = "select * from Movimiento m where m.id_cuenta = ?1 and m.fecha = ?2", nativeQuery = true)
    List<Movimiento> findBycuentaIdFecha(int cuentaId, Date fecha);

}
