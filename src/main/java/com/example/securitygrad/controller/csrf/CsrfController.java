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

   Nie musisz używać CSRF, jeśli:
	-	Używasz JWT (Bearer token) do uwierzytelniania,
	-	Token jest przechowywany po stronie klienta (np. w localStorage),
	-	Aplikacja działa w trybie stateless (czyli backend nie przechowuje sesji).

   Dlaczego CSRF nie jest potrzebne z JWT + HTTPS?
   CSRF polega na wykorzystaniu zaufanej przeglądarki, która automatycznie dołącza np. ciasteczka (cookies) do zapytań – nawet gdy są wysłane z innego źródła (np. złośliwego skryptu z innej strony).

   Ale jeśli:
	-	Uwierzytelnianie odbywa się przez nagłówek Authorization: Bearer <token>,
	-	Token nie jest automatycznie dodawany przez przeglądarkę (bo nie jest ciasteczkiem),
	-	I dodatkowo połączenie jest zabezpieczone przez HTTPS,

    to atak CSRF nie ma szans zadziałać, bo atakujący nie ma jak dołączyć tego tokena w nagłówku.
     */
    @GetMapping("/get-csrf-token")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }
}
