package com.dauphine.Work_Nest_backend.controllers;

import com.dauphine.Work_Nest_backend.dto.LoginRequest;
import com.dauphine.Work_Nest_backend.dto.LoginResponse;
import com.dauphine.Work_Nest_backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user == null) {
            // Email ou mot de passe invalide → Unauthorized
            return ResponseEntity.status(401).body(
                    java.util.Collections.singletonMap("message", "Email ou mot de passe invalide")
            );
        }

        // Retourner simplement l'utilisateur
        return ResponseEntity.ok(user);
    }
}

