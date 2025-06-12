package com.cybershoes.sistema_venta.service;

import java.util.List;
import java.util.Optional;

import com.cybershoes.sistema_venta.model.Venta;

public interface VentaService {

    Venta registrarVenta(Venta venta);
    List<Venta> listarTodos();
    Optional<Venta> obtenerPorId(Long id);
    void eliminar(Long id);
    
}
