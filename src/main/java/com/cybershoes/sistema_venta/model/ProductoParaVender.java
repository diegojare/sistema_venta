package com.cybershoes.sistema_venta.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoParaVender extends Producto {

    private int cantidad;

	//Agregar constructor en entidad Producto
    public ProductoParaVender(Long idProducto, String codigo, String descripcion,
    		BigDecimal precioCompra, BigDecimal precioVenta , int stock, int cantidad) {
        super(idProducto,codigo,descripcion, precioCompra, precioVenta, stock);
        this.cantidad = cantidad;
    }

    public void aumentarCantidad() {
        this.cantidad++;
    }
    
    public float getTotal() {
        return this.getPrecioVenta().multiply(new BigDecimal(this.cantidad)).floatValue();
    }

}
