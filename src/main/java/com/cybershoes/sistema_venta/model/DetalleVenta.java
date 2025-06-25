package com.cybershoes.sistema_venta.model;

import java.math.BigDecimal;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetalleVenta {

    @EmbeddedId
    private DetalleVentaId idDetalleVenta;

	private int cantidadVta;
	
	private BigDecimal precioVta;
	
	private BigDecimal importeVta;
		
	private BigDecimal precioCompra;
}
