package com.example.securitygrad.controller.csrf;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/csrf")
public class CsrfController {

    /*
   Jezeli chcesz uzywać CSRF, to musisz właczyć go w konfiguracji.
   Zakomentuj ponizszą linię  w klasie: SecurityConfiguration, metoda: securityFilterChain():
   .csrf(AbstractHttpConfigurer::disable)
     */
    @GetMapping("/get-csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
