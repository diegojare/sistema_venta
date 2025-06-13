package com.cybershoes.sistema_venta.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cybershoes.sistema_venta.model.Rol;
import com.cybershoes.sistema_venta.model.Usuario;
import com.cybershoes.sistema_venta.service.RolService;
import com.cybershoes.sistema_venta.service.UsuarioService;

@Controller
@RequestMapping("/menuAd/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, RolService rolService){
        this.usuarioService = usuarioService;
        this.rolService = rolService;
        
    }

    @GetMapping
    public String usuario(Model model, @RequestParam(required = false) String filtro) {
        List<Usuario> usuarios;

        if (filtro != null && !filtro.isBlank()) {
            usuarios = usuarioService.listarPorUsername(filtro);
        } else {
            usuarios = usuarioService.listarTodos();
        }

        List<Rol> roles = rolService.listarTodos();
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        return "usuario/usuario";
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/menuAd/usuario";
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = usuarioService.obtenerPorId(id);
        List<Rol> roles = rolService.listarTodos();
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", roles);
        return "usuario/usuario_editar";
    }

    @PostMapping("/editar")
    public String editarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/menuAd/usuario";
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return "redirect:/menuAd/usuario";
    }

}
