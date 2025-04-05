package com.example.securitygrad.jwtauth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// Ten kontroler jest zabezpieczony i wymaga Bearer Token (JWT)
@RestController
@RequestMapping("api/v1/demo-controller")
public class SecuredDataWithJWTController {

    @GetMapping
    public ResponseEntity<Map<String, String>> sayHello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello from secured endpoint");
        return ResponseEntity.ok(response);  // Zwracamy Map w odpowiedzi, co Spring automatycznie zamieni na JSON
    }
}
