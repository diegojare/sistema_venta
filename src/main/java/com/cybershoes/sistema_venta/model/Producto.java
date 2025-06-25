package com.cybershoes.sistema_venta.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

	private String codigo;
	
	private String descripcion;
		
	private BigDecimal precioCompra;
	
	private BigDecimal precioVenta;
	
	private Integer stock;

    public Producto(String codigo) {
		this.codigo = codigo;
	}

    public void restarExistencia(int stock) {
	    this.stock -= stock;
	}
	    
	public boolean sinExistencia() {
	    return this.stock <= 0;
	}

}