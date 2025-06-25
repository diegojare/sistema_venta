package com.cybershoes.sistema_venta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybershoes.sistema_venta.model.DetalleVenta;
import com.cybershoes.sistema_venta.model.DetalleVentaId;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, DetalleVentaId> {

    @Query(value="SELECT p.nro_venta, p.id_producto, p.cantidad_vta, p.precio_vta, p.importe_vta, "
			+ "p.precio_compra FROM detalle_venta p where p.nro_venta = :nroVenta",nativeQuery = true)
	List<DetalleVenta> buscarByNroVenta(Long nroVenta);

}
