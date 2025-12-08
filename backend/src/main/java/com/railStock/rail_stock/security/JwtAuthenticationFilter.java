package com.railStock.rail_stock.security;


import com.railStock.rail_stock.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT Authentication Filter - Das "Ausweis-Lesegerät" unserer Applikation
 *
 * Dieser Filter wird bei JEDEM Request ausgeführt und macht folgendes:
 * 1. Schaut ob ein JWT Token im Authorization Header ist
 * 2. Validiert den Token (Signatur, Ablaufdatum)
 * 3. Lädt den User aus der Datenbank
 * 4. Setzt den User in den SecurityContext (Spring weiss jetzt: User ist eingeloggt!)
 *
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructor Injection - Spring gibt uns automatisch:
     * - JwtService (zum Token validieren)
     * - UserDetailsService (zum User laden)
     */
    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Diese Methode wird bei JEDEM Request ausgeführt!
     *
     * @param request  Der eingehende HTTP Request
     * @param response Die HTTP Response
     * @param filterChain Die Chain von weiteren Filtern
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        // SCHRITT 1: Authorization Header aus Request holen
        final String authHeader = request.getHeader("Authorization");

        // SCHRITT 2: Prüfen ob Header existiert und mit "Bearer " startet
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            //Kein Token -> Filter überspringen
            //weiter mit nächstem Filter
            filterChain.doFilter(request, response);
            return;
        }

        // SCHRITT 3: Token aus dem Header extrahieren
        final String jwt = authHeader.substring(7);

        // SCHRITT 4: Username aus dem Token extrahieren
        final String username = jwtService.extractUsername(jwt);

        // SCHRITT 5: Prüfen ob User existiert UND
        // noch nicht authentifiziert ist
        // SecurityContextHolder.getContext()
        if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // SCHRITT 6: User-Details aus Datenbank laden
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // SCHRITT 7: Token validieren (Signatur + Ablaufdatum prüfen)
            if (jwtService.validateToken(jwt, username)){
                // SCHRITT 8: Authentication Object erstellen
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // SCHRITT 9: Request-Details hinzufügen
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // SCHRITT 10: User in SecurityContext setzen
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // SCHRITT 11: Weiter zum nächsten Filter in der Chain
        filterChain.doFilter(request, response);
    }
}
