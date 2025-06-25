package com.cybershoes.sistema_venta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybershoes.sistema_venta.model.Cliente;
import com.cybershoes.sistema_venta.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/recepcionista/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public String cliente(Model model, @RequestParam(required = false) String filtro) {
        List<Cliente> clientes;

        if (filtro != null && !filtro.isBlank()) {
            clientes = clienteService.listarPorDniONombreOApellido(filtro);
        } else {
            clientes = clienteService.listarTodos();
        }

        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", clientes);
        return "cliente/cliente";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/recepcionista/cliente";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(@PathVariable Long id, Model model) {
        Optional<Cliente> cliente = clienteService.obtenerPorId(id);
        model.addAttribute("cliente", cliente);
        return "cliente/cliente_editar";
    }

    @PostMapping("/editar")
    public String editarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/recepcionista/cliente";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/recepcionista/cliente";
    }
    
    
    
}
