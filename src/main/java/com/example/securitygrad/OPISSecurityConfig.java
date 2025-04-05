package com.example.securitygrad;

//@Configuration
public class OPISSecurityConfig {

    // Opis konfiguracji z wszystkimi zabezpieczeniami
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // 1. Ochrona przed CSRF (domyślnie włączona)
//                // Dlatego usuń tą linię lub zostaw zakomentowaną jeśli chcesz właczyć--> .csrf(AbstractHttpConfigurer::disable)
//                // UWAGA: Jeśli używasz REST API, lepiej wyłączyć CSRF i zabezpieczać API poprzez JWT lub OAuth2:
//
//                // 2. Ochrona przed osadzaniem w iframe (Clickjacking, Clockjacking)
//                .headers(headers -> headers
//                        .frameOptions(frameOptions -> frameOptions.deny()) // Blokowanie iframe
//                        .contentSecurityPolicy(csp -> csp.policyDirectives("frame-ancestors 'self'")) // CSP dla ramek
//                )
//
//                // 3. Ochrona przed XSS (Cross-Site Scripting)
//                .headers(headers -> headers
//                        .xssProtection(xss -> xss.disable()) // XSS Protection (przeglądarki już to obsługują)
//                        .contentSecurityPolicy(csp -> csp.policyDirectives(
//                                "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; object-src 'none'"
//                        )) // CSP dla treści
//                )
//
//                // 4. Ochrona przed sniffingiem MIME types
//                .headers(headers -> headers
//                        .contentTypeOptions(withDefaults()) // X-Content-Type-Options: nosniff
//                )
//
//                // 5. HSTS (wymuszanie HTTPS)
//                .headers(headers -> headers
//                        .httpStrictTransportSecurity(hsts -> hsts
//                                .includeSubDomains(true)
//                                .maxAgeInSeconds(31536000) // 1 rok
//                                .preload(true)
//                        )
//                )
//
//                // 6. Ograniczenie CORS (jeśli API, trzeba dostosować)
//                .cors(withDefaults())
//
//                // 7. Sesje (stateless dla API, lub default)
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // STATELESS dla API, IF_REQUIRED dla sesji
//                )
//
//                // 8. Uwierzytelnianie i autoryzacja
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin/**").authenticated() // Admin wymaga logowania
//                        .requestMatchers("/public/**").permitAll() // Publiczne endpointy
//                        .anyRequest().authenticated()
//                )
//
//                // 9. Logowanie użytkownika
//                .formLogin(withDefaults())
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/")
//                );
//
//        return http.build();
//    }

}