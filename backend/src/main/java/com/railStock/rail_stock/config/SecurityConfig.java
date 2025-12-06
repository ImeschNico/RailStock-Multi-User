package com.railStock.rail_stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security Konfiguration - Der "Bauplan" für unser Sicherheitssystem
 *
 * Analogie: Das ist wie der Sicherheitsplan eines Bürogebäudes, der festlegt:
 * - Welche Bereiche sind öffentlich? (Empfangshalle)
 * - Welche Bereiche brauchen einen Ausweis? (Büros)
 * - Wo werden die Ausweis-Lesegeräte installiert? (JWT Filter)
 */
@Configuration  // Spring scannt diese Klasse beim Start
@EnableWebSecurity  // Aktiviert Spring Security
@EnableMethodSecurity // <- Spring: "Erlaube @PreAuthorize auf Methoden"
public class SecurityConfig {

    /**
     * Bean #1: Password Encoder
     *
     * - Niemand kann das Original-Passwort lesen
     * - BCrypt ist der Verschlüsselungsalgorithmus (sehr sicher!)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
    //Work Factor: 10-12 ist Standard 14+ für hochsensible Daten
        return new BCryptPasswordEncoder(12);
    }

    /**
     * Bean #2: Authentication Manager
     *
     * - Prüft ob Username + Passwort stimmen
     * - Gibt bei Erfolg einen JWT Token aus
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();
    }


    /**
     * Security Filter Chain Configuration.
     *
     * TEMPORÄR: Alle Requests erlauben für Entwicklung
     * SPÄTER: JWT Authentication hinzufügen
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        // CSRF für REST APIs deaktivieren
        // (verwenden JWT stattdessen)
                // SCHRITT 1: CSRF deaktivieren
                .csrf(csrf -> csrf.disable())

                // SCHRITT 2: CORS konfigurieren
                .cors(cors -> cors.configure(http))

                // SCHRITT 3: Authorization Rules
                //Request Authorization Rules
                .authorizeHttpRequests(auth -> auth
                        // 🌍 ÖFFENTLICHE Bereiche (wie die Empfangshalle)
                        .requestMatchers(
                                "/api/auth/**",      // Login & Register - jeder darf rein
                                "/swagger-ui/**",    // API Dokumentation - öffentlich
                                "/v3/api-docs/**"    // OpenAPI Docs - öffentlich
                        ).permitAll()

                        // 🔒 ALLE anderen Endpoints benötigen
                        // einen gültigen Ausweis (JWT)
                        .anyRequest().authenticated()
                )

                // SCHRITT 4: Session Management auf STATELESS setzen
                // Warum? Wir benutzen JWT, nicht Sessions/Cookies
                // Analogie: Keine Besucherliste führen, nur Ausweise prüfen
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
