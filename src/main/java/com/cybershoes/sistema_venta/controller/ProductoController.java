package com.cybershoes.sistema_venta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybershoes.sistema_venta.model.Producto;
import com.cybershoes.sistema_venta.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/menuA/producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String producto(Model model, @RequestParam(required = false) String filtro) {
        List<Producto> productos;

        if (filtro != null && !filtro.isBlank()) {
            productos = productoService.listarPorNombreOMarca(filtro);
        } else {
            productos = productoService.listarTodos();
        }


        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", productos);
        return "producto/producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/menuA/producto";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Long id, Model model) {
        Optional<Producto> producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return "producto/producto_editar";
    }

    @PostMapping("/editar")
    public String editarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/menuA/producto";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/menuA/producto";
    }
}