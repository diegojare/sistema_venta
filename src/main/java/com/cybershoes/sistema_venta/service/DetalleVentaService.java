package com.cybershoes.sistema_venta.service;

import java.util.List;

import com.cybershoes.sistema_venta.model.DetalleVenta;

public interface DetalleVentaService {

    DetalleVenta guardarDetalleVenta(DetalleVenta detalleVenta);

	List<DetalleVenta> listarTodosDetalleVenta();
	
    List<DetalleVenta> buscarDetalleVentaByNroVenta(Long nroVenta);

}
