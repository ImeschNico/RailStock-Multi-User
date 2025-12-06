package com.railStock.rail_stock.service;

import com.railStock.rail_stock.entity.AppUser;
import com.railStock.rail_stock.entity.Role;
import com.railStock.rail_stock.repository.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * Service Layer für User-Management.
 *
 * Verantwortlichkeiten:
 * - User Registration mit Password Hashing
 * - Username/Email Validierung
 * - User Authentication (Vorbereitung für Login)
 *
 * @Service markiert diese Klasse als Spring Service Component
 * @Transactional macht alle Methoden transaktional (Alles oder Nichts)
 */
@Service
@Transactional
public class AppUserService {

    // Dependencies via Constructor Injection
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor Injection statt @Autowired
     * Vorteile:
     * - Explizite Dependencies
     * - Einfacher zu testen (Mock injection)
     * - Immutable fields (final)
     */
    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registriert einen neuen User.
     *
     * Business Rules:
     * 1. Username muss unique sein
     * 2. Email muss unique sein
     * 3. Password wird gehashed gespeichert
     * 4. Default Role ist User
     *
     * @param username Der gewünschte Username
     * @param email Die Email-Adresse
     * @param rawPassword Das Klartext-Passwort (wird gehashed)
     * @param role Die Rolle (ADMIN oder USER)
     * @return Der gespeicherte User mit generierter ID
     * @throws IllegalArgumentException wenn Username/Email
    bereits existiert
     */
    public AppUser registerUser(String username, String email, String rawPassword, Role role){
        // Validation 1: Username bereits vergeben?
            if (appUserRepository.existsByUsername(username)){
                throw new IllegalArgumentException("Username " +username + "bereits vergeben");
            }
        // Validation 2: Email bereits registriert?
        if (appUserRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email " +email + "ist bereits registrert");
        }

        // Validation 3: Basis-Checks


        // Password hashen - NIEMALS Klartext speichern!
        String hashedPassword = passwordEncoder.encode(rawPassword);

        // User Entity erstellen
        AppUser newUser = new AppUser(username, email, hashedPassword, role);

        // Speichern und zurückgeben (mit generierter ID)
        return appUserRepository.save(newUser);
    }

    /**
     * Findet einen User by Username.
     *
     * @param username Der Username
     * @return Optional mit User oder empty
     */
    public Optional<AppUser> findByUsername(String username){
        return appUserRepository.findByUsername(username);
    }

    /**
     * Findet einen User by Email.
     *
     * Wird benötigt für Login mit Email.
     *
     * @param email Die Email
     * @return Optional mit User oder empty
     */
    public Optional<AppUser> findByEmail(String email){
        return appUserRepository.findByEmail(email);
    }

    /**
     * Authentifiziert einen User (Vorbereitung für Login).
     *
     * @param username Der Username
     * @param rawPassword Das eingegebene Passwort
     * @return Optional mit User wenn Login erfolgreich, sonst empty
     */
    public Optional<AppUser> authenticateUser(String username, String rawPassword){

        //User suchen
        Optional<AppUser> userOpt = appUserRepository.findByUsername(username);

        if (userOpt.isPresent()){
            AppUser user = userOpt.get();
            //Passwort prüfen mit ByCrypt
            if (passwordEncoder.matches(rawPassword, user.getPassword())){
                //Login erflgreich
                return userOpt;

            }
        }
        //Login fehlgeschlagen
        return Optional.empty();
    }
    /**
     * Hilfsmethode: Email-Validation.
     *
     * Einfache Prüfung - für Production besser:
     * - javax.validation.constraints.Email
     * - Apache Commons Validator
     *
     * @param email Die zu prüfende Email
     * @return true wenn Email-Format gültig
     */
    private boolean isValidEmail(String email){
        // Einfache Regex für Email-Validation
        return email != null &&
                email.contains("@") &&
                email.length() > 3;
    }

    /**
     * Prüft ob ein Username verfügbar ist.
     *
     * @param username Der zu prüfende Username
     * @return true wenn verfügbar
     */
    public boolean isUsernameAvailable(String username){
        return !appUserRepository.existsByUsername(username);
    }

    /**
     * Prüft ob eine Email verfügbar ist.
     *
     * @param email Die zu prüfende Email
     * @return true wenn verfügbar
     */
    public boolean isEmailAvailable(String email){
        return !appUserRepository.existsByEmail(email);
    }
}
