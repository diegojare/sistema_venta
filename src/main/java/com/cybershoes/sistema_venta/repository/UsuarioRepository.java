package com.cybershoes.sistema_venta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybershoes.sistema_venta.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
    Usuario findByUsernameAndClave(String username, String clave);
}
