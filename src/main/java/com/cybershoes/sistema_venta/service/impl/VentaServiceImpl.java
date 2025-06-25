package com.cybershoes.sistema_venta.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybershoes.sistema_venta.model.Venta;
import com.cybershoes.sistema_venta.repository.VentaRepository;
import com.cybershoes.sistema_venta.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }
    
    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> obtenerPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public void eliminar(Long id) {
        ventaRepository.deleteById(id);
    }

}
