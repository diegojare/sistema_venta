package com.cybershoes.sistema_venta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybershoes.sistema_venta.model.DetalleVenta;
import com.cybershoes.sistema_venta.repository.DetalleVentaRepository;
import com.cybershoes.sistema_venta.service.DetalleVentaService;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaServiceImpl(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public List<DetalleVenta> listarTodosDetalleVenta() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public List<DetalleVenta> buscarDetalleVentaByNroVenta(Long nroVenta) {
        return detalleVentaRepository.buscarByNroVenta(nroVenta);
    }

    
   

}
