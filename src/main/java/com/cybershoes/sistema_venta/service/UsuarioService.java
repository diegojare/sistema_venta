package com.cybershoes.sistema_venta.service;

import java.util.List;
import java.util.Optional;

import com.cybershoes.sistema_venta.model.Usuario;

public interface UsuarioService {

    List<Usuario> listarTodos();
    Optional<Usuario> obtenerPorId(Long id);
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);

    Usuario login (Usuario usuario);
}
