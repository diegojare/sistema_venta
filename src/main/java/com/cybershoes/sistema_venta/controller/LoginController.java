package com.cybershoes.sistema_venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cybershoes.sistema_venta.model.Usuario;
import com.cybershoes.sistema_venta.service.UsuarioService;

@Controller
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }

    @GetMapping
    public String relogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("usuario") Usuario usuario) {
        Usuario user = usuarioService.login(usuario);
        if (user != null) {
            if (user.getRol().getIdRol() == 1) {
                return "redirect:/menuV";
            } else {
                return "redirect:/menuA";
            }
        } else{
            return "index";
        }
    }

}
