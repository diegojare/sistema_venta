package com.cybershoes.sistema_venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cybershoes.sistema_venta.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
