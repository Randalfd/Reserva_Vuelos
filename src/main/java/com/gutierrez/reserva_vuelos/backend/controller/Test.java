package com.gutierrez.reserva_vuelos.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/")
    public String Test() {
        return "Hola Mundo";
    }
}
