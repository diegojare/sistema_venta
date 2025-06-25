package com.cybershoes.sistema_venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MenuController {

    @GetMapping("/recepcionista")
    public String menuV() {
        return "menu/menuV";
    }

    @GetMapping("/admin")
    public String menuAd() {
        return "menu/menuAd";
    }
    
}
