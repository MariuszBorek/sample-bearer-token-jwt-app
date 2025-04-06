package com.example.securitygrad.jwtauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 0. Ograniczenie CORS
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                // 1. Ochrona przed CSRF (domyślnie włączona) - Nie trzeba jezeli używasz JWT
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Ochrona przed osadzaniem w iframe (Clickjacking, Clockjacking)
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.deny()) // Blokowanie iframe
                        .contentSecurityPolicy(csp -> csp.policyDirectives("frame-ancestors 'self'")) // CSP dla ramek
                )
                // 3. Ochrona przed XSS (Cross-Site Scripting)
                .headers(headers -> headers
                        .xssProtection(xss -> xss.disable()) // XSS Protection (przeglądarki już to obsługują)
                        .contentSecurityPolicy(csp -> csp.policyDirectives(
                                "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data:; object-src 'none'"
                        )) // CSP dla treści
                )

                // 4. Ochrona przed sniffingiem MIME types
                .headers(headers -> headers
                        .contentTypeOptions(withDefaults()) // X-Content-Type-Options: nosniff
                )

                // 5. HSTS (wymuszanie HTTPS)
                .headers(headers -> headers
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .maxAgeInSeconds(31536000) // 1 rok
                                .preload(true)
                        )
                )

                // 6. Uwierzytelnianie i autoryzacja
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("api/v1/auth/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())

                // 7. Sesje (stateless dla API, lub default)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // STATELESS dla API, IF_REQUIRED dla sesji

                .authenticationProvider(authenticationProvider)
                // 8. Filtr JWT
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Konfiguracja CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:3443"));  // Frontend URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Dozwolone metody
        configuration.setAllowedHeaders(Arrays.asList("*", "Authorization", "Content-Type", "X-CSRF-TOKEN"));  // Dozwolone nagłówki
        configuration.setAllowCredentials(true);  // Jeśli używasz ciasteczek/tokenów

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Rejestracja dla wszystkich endpointów
        return source;
    }

}
