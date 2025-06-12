package com.cybershoes.sistema_venta.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Producto producto;

    private int cantidad;
    private double total;
    private LocalDate fecha;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
        this.total = this.cantidad * this.producto.getPrecio();
    }
}
