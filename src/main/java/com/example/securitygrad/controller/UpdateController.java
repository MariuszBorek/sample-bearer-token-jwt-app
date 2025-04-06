package com.example.securitygrad.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/demo-update-controller")
public class UpdateController {

    @PostMapping("update")
    public ResponseEntity<Map<String, String>> updateProfile(@RequestBody Map<String, String> request, HttpServletResponse response) {
        System.out.println("Request body: " + request);

        // Przykładowe ciasteczko
        Cookie cookie = new Cookie("myCookie", "myCookieValue");
        cookie.setHttpOnly(true); // opcjonalnie - tylko dla HTTP, niedostępne z JS
        cookie.setSecure(true); // HTTPS only
        cookie.setPath("/"); // dostępne w całej aplikacji
        cookie.setMaxAge(3600); // 1 godzina

        response.addCookie(cookie); // dodajemy do odpowiedzi

        return ResponseEntity.ok(request);
    }

    @GetMapping("sample-get-button")
    public ResponseEntity<Map<String, String>> getCookie() {
        return ResponseEntity.ok(Map.of("message", "Cookie set successfully"));
    }
}
