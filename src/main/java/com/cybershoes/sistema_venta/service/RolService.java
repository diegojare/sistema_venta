package com.cybershoes.sistema_venta.service;

import java.util.List;
import java.util.Optional;

import com.cybershoes.sistema_venta.model.Rol;

public interface RolService {

    List<Rol> listarTodos();
    Optional<Rol> obtenerPorId(Long id);
    Rol guardar(Rol rol);
    void eliminar(Long id);
}
