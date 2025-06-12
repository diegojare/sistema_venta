package com.cybershoes.sistema_venta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/menu")
public class MenuController {

    @GetMapping("")
    public String menu() {
        return "menu";
    }
    
}
