package com.railStock.rail_stock.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfigurationsklasse für globale Web-Einstellungen.
 * <p>
 * Enthält Konfigurationen für CORS (Cross-Origin Resource Sharing), um den Zugriff
 * auf die REST-Endpunkte von bestimmten Ursprüngen zu erlauben.
        * </p>
        *
        * Autor: Nico Imesch
 * Version: 1.0
        */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Konfiguriert globale CORS-Regeln für alle REST-Endpunkte.
     * <p>
     * Erlaubt HTTP-Anfragen vom Ursprung "http://localhost:5173" mit den Methoden
     * GET, POST, PUT, DELETE und OPTIONS. Alle Header sind erlaubt. Cookies werden
     * nicht mitgesendet. Die maximale Preflight-Zeit beträgt 3600 Sekunden.
     * </p>
     *
     * @param registry das {@link CorsRegistry} Objekt zur Registrierung der Regeln
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }

    // SPA-Routing: alle Pfade außer /api/** auf index.html weiterleiten
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/{spring:[^.]+}").setViewName("forward:/index.html");
        registry.addViewController("/**/{spring:[^.]+}").setViewName("forward:/index.html");
    }

}


