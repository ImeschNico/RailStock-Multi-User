package com.railStock.rail_stock.controller;


import com.railStock.rail_stock.dto.RegisterRequestDTO;
import com.railStock.rail_stock.dto.RegisterResponseDTO;
import com.railStock.rail_stock.entity.AppUser;
import com.railStock.rail_stock.entity.Role;
import com.railStock.rail_stock.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller für Authentication und Registration.
 *
 * Verantwortlichkeiten des Controllers:
 * - HTTP Request/Response Handling
 * - Status Codes setzen
 * - DTOs zu Service-Calls mappen
 * - Exceptions zu HTTP Responses konvertieren
 *
 * @RestController = @Controller + @ResponseBody
 *                   Alle Methoden returnen automatisch JSON
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AppUserService appUserService;

    // Constructor Injection
    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    /**
     * POST /api/auth/register
     *
     * Registriert einen neuen User.
     *
     * @Valid triggert die Bean Validation
     * @RequestBody parsed JSON zu Java Object
     *
     * Request Body Example:
     * {
     *   "username": "maxmuster",
     *   "email": "max@example.com",
     *   "password": "secure123"
     * }
     *
     * Success Response (200):
     * {
     *   "id": 1,
     *   "username": "maxmuster",
     *   "email": "max@example.com",
     *   "role": "USER",
     *   "message": "Registrierung erfolgreich! Willkommen maxmuster!"
     * }
     *
     * Error Response (400):
     * {
     *   "error": "Username 'maxmuster' ist bereits vergeben"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO request){
        try{
            // Service aufrufen mit den DTO Daten
            AppUser newUser = appUserService.registerUser(
                    request.getUsername(),
                    request.getEmail(),
                    request.getPassword(),
                    Role.USER
            );

            // Success Response DTO erstellen
            RegisterResponseDTO response = new RegisterResponseDTO(
                    newUser.getId(),
                    newUser.getUsername(),
                    newUser.getEmail(),
                    newUser.getRole().name(),
                    "Registrierung erfolgreich! Willkommen "+newUser.getUsername()+"!"
            );

            // HTTP 200 OK mit Response Body
            return ResponseEntity.ok(response);

        }
        catch (IllegalArgumentException e){
            // Business Validation Fehler (Username exists, etc.)
            // HTTP 400 Bad Request
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);

        }
        catch (Exception e){
            // Unerwartete Fehler
            // HTTP 500 Internal Server Error
            Map<String, String> error = new HashMap<>();
            error.put("error", "Registrierung fehlgeschlagen");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * GET /api/auth/check-username/{username}
     * Prüft, ob ein Username verfügbar ist.
     * Nützlich für Live-Validation im Frontend.
     *
     * Response:
     * {
     *   "available": true/false,
     *   "message": "Username ist verfügbar" /
     *              "Username bereits vergeben"
     * }
     */
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Map<String, Object>> checkUsername(@PathVariable String username){
        boolean available = appUserService.isUsernameAvailable(username);
        Map<String, Object> response = new HashMap<>();
        response.put("available", available);
        response.put("message", available ? "Username ist verfügbar" : "Username ist bereits vergeben");
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/auth/check-email/{email}
     *
     * Prüft ob eine Email verfügbar ist.
     *
     * Response:
     * {
     *   "available": true/false,
     *   "message": "Email ist verfügbar" / "Email bereits registriert"
     * }
     */
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Object>> checkEmail(@PathVariable String email){
        boolean available = appUserService.isEmailAvailable(email);
        Map<String, Object> response = new HashMap<>();
        response.put("available", available);
        response.put("message", available ? "Email ist verfügbar":"Email ist bereits vergeben");
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/auth/test
     *
     * Simple Test Endpoint.
     * Prüft ob der Controller erreichbar ist.
     */
    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("AuthController funktioniert!");
    }
}
