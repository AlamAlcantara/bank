package com.alam.bank.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimiento extends JpaRepository<TipoMovimiento, Integer> {
}
