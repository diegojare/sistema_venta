package com.cybershoes.sistema_venta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cybershoes.sistema_venta.model.Producto;
import com.cybershoes.sistema_venta.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/menu/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String producto(Model model) {
        List<Producto> productos = productoService.listarTodos();
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productos);
        return "producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/menu/producto";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return "producto_editar";
    }

    @PostMapping("/editar")
    public String editarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/menu/producto";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/menu/producto";
    }
}