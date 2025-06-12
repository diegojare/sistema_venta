package com.cybershoes.sistema_venta.service;

import java.util.List;
import java.util.Optional;

import com.cybershoes.sistema_venta.model.Cliente;

public interface ClienteService {

    List<Cliente> listarTodos();
    Optional<Cliente> obtenerPorId(Long id);
    Cliente guardar(Cliente cliente);
    void eliminar(Long id);
    
}
