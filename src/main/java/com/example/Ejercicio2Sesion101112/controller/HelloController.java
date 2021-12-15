package com.example.Ejercicio2Sesion101112.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hola")
    public String saludo (){
        return "Saludos desde HelloController";
    }

}
