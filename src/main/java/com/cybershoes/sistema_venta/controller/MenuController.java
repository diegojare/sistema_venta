package com.cybershoes.sistema_venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MenuController {

    @GetMapping("/menuV")
    public String menuV() {
        return "menu/menuV";
    }

    @GetMapping("/menuA")
    public String menuA() {
        return "menu/menuA";
    }

    @GetMapping("/menuAd")
    public String menuAd() {
        return "menu/menuAd";
    }
    
}
