package com.cybershoes.sistema_venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cybershoes.sistema_venta.model.Venta;

public interface VentaRepository extends JpaRepository<Venta, Long> {

}
