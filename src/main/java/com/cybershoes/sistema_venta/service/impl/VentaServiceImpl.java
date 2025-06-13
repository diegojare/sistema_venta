package com.cybershoes.sistema_venta.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybershoes.sistema_venta.model.Producto;
import com.cybershoes.sistema_venta.model.Venta;
import com.cybershoes.sistema_venta.repository.ProductoRepository;
import com.cybershoes.sistema_venta.repository.VentaRepository;
import com.cybershoes.sistema_venta.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    public VentaServiceImpl(VentaRepository ventaRepository, ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
    }
    
    @Override
    @Transactional
    public Venta registrarVenta(Venta venta) {
        Producto producto = venta.getProducto();
        if (producto.getStockProd() < venta.getCantidad()) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        producto.setStockProd(producto.getStockProd() - venta.getCantidad());
        productoRepository.save(producto);
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
