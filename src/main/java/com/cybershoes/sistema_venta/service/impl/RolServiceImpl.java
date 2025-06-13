package com.cybershoes.sistema_venta.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cybershoes.sistema_venta.model.Rol;
import com.cybershoes.sistema_venta.repository.RolRepository;
import com.cybershoes.sistema_venta.service.RolService;

@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl (RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }

}
