package com.cybershoes.sistema_venta.service;

import java.util.List;
import java.util.Optional;

import com.cybershoes.sistema_venta.model.Producto;

public interface ProductoService {

    List<Producto> listarTodos();
    Optional<Producto> obtenerPorId(Long id);
    Producto guardar(Producto producto);
    void eliminar(Long id);
}
