package com.example.securitygrad.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/demo-csrf-controller")
public class CsrfSecurderDataController {

    // todo: nie działa
    // Ten kontroler jest zabezpieczony i wymaga Bearer Token (JWT) oraz CSRF
    @PostMapping
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody Map<String, String> request) {
        System.out.println("Request body: " + request);
        return ResponseEntity.ok(request);
    }
}
