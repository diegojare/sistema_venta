package com.cybershoes.sistema_venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cybershoes.sistema_venta.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByDniClienteStartingWithOrNomClienteContainingIgnoreCaseOrApeClienteContainingIgnoreCase(String dniCliente, String nombre, String apellido);
}
