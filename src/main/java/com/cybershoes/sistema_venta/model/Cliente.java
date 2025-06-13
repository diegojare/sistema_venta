package com.cybershoes.sistema_venta.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nomCliente;
    private String apeCliente;
    @Pattern(regexp = "^\\d{1,8}$", message = "El DNI debe tener exactamente 8 dígitos")
    private String dniCliente;
    private String sexoCliente;
    private String emailCliente;
    @Pattern(regexp = "\\d{1,9}", message = "El telefono debe tener 9 dígitos")
    private String telefCliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecNacimCliente;

}
