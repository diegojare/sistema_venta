package com.cybershoes.sistema_venta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cybershoes.sistema_venta.model.Cliente;
import com.cybershoes.sistema_venta.model.Producto;
import com.cybershoes.sistema_venta.model.Venta;
import com.cybershoes.sistema_venta.service.ClienteService;
import com.cybershoes.sistema_venta.service.ProductoService;
import com.cybershoes.sistema_venta.service.VentaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/menu/venta")
public class VentaController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final ProductoService productoService;


    public VentaController(VentaService ventaService, ClienteService clienteService, ProductoService productoService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @GetMapping
    public String venta(Model model) {
        List<Venta> ventas = ventaService.listarTodos();
        List<Cliente> clientes = clienteService.listarTodos();
        List<Producto> productos = productoService.listarTodos();
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);
        model.addAttribute("ventas", ventas);
        return "venta";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute Venta venta) {
        ventaService.registrarVenta(venta);
        return "redirect:/menu/venta";
    }

    @GetMapping("/editar/{id}")
    public String editarVenta(@PathVariable Long id, Model model) {
        Optional<Venta> venta = ventaService.obtenerPorId(id);
        model.addAttribute("venta", venta);
        return "venta_editar";
    }

    @PostMapping("/editar")
    public String editarVenta(@ModelAttribute Venta venta) {
        ventaService.registrarVenta(venta);
        return "redirect:/menu/venta";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
        return "redirect:/menu/venta";
    }
}