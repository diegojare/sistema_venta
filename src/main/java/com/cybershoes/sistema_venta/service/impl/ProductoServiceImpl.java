package com.cybershoes.sistema_venta.service.impl;

import java.util.List;
import java.util.Optional;

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
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> listarPorNombreOMarca(String filtro) {
        return productoRepository.findByNomProdStartingWithIgnoreCaseOrMarcaProdContainingIgnoreCase(filtro, filtro);
    }
}
