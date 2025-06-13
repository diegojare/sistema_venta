package com.cybershoes.sistema_venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybershoes.sistema_venta.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario>findByUsername(String username);
    Usuario findByUsernameAndClave(String username, String clave);
}
