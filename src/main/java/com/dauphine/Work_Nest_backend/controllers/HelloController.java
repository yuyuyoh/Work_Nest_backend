package com.dauphine.Work_Nest_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Bienvenue sur l'API backend Spring Boot !";
    }

    @GetMapping("/test")
    public String test() {
        return "Test OK sur le port 8081 !";
    }
}

