package com.cybershoes.sistema_venta.service;

import java.util.List;

import com.cybershoes.sistema_venta.model.Producto;

public interface ProductoService {

    List<Producto> listarTodosProductos();
    Producto buscarProductoById(Long id);
    Producto guardarProducto(Producto producto);
    Producto actualizarProducto(Producto producto);
    void eliminarProductoById(Long id);
    
    List<Producto> listarPorNombreOMarca(String filtro);
    Producto buscarProductoByCodigo(String codProd);
}
