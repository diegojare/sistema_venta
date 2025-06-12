package com.cybershoes.sistema_venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cybershoes.sistema_venta.service.ClienteService;
import com.cybershoes.sistema_venta.service.ProductoService;
import com.cybershoes.sistema_venta.service.VentaService;

@Controller
@RequestMapping("/menu/venta")
public class VentaController {

    private final VentaService ventaService;
    private final ProductoService productoService;
    private final ClienteService clienteService;

    public VentaController(VentaService ventaService, ClienteService clienteService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.productoService = productoService;
        this.clienteService = clienteService;
    }

    
}
