package com.cybershoes.sistema_venta.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cybershoes.sistema_venta.model.Producto;
import com.cybershoes.sistema_venta.repository.ProductoRepository;
import com.cybershoes.sistema_venta.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarTodosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoById(Long id) {
        return productoRepository.findById(id).get();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProductoById(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> listarPorNombreOMarca(String filtro) {
        return productoRepository.findByDescripcionStartingWithIgnoreCase(filtro);
    }

    @Override
    public Producto buscarProductoByCodigo(String codProd) {
        return productoRepository.findByCodigo(codProd);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }
}
