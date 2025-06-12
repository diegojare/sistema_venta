package com.cybershoes.sistema_venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cybershoes.sistema_venta.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
