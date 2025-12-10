package com.railStock.rail_stock;

import com.railStock.rail_stock.entity.AppUser;
import com.railStock.rail_stock.entity.Role;
import com.railStock.rail_stock.repository.AppUserRepository;
import com.railStock.rail_stock.service.AppUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceUnitTest {

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AppUserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Testdaten
        String username = "nico";
        String email = "nico@test.de";
        String rawPassword = "secret";
        Role role = Role.USER;

        AppUser user = new AppUser(username, email, "encodedPassword", role);

        // Mocking
        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(passwordEncoder.encode(rawPassword)).thenReturn("encodedPassword");
        when(userRepository.save(any(AppUser.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Testaufruf
        AppUser result = userService.registerUser(username, email, rawPassword, role);

        // Assertions
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(email, result.getEmail());
        assertEquals("encodedPassword", result.getPassword());

        // Verify
        verify(userRepository, times(1)).save(any(AppUser.class));
        verify(passwordEncoder, times(1)).encode(rawPassword);
    }
}
