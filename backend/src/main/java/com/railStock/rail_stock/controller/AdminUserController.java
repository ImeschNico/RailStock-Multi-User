package com.railStock.rail_stock.controller;

import com.railStock.rail_stock.dto.AdminUserDTO;
import com.railStock.rail_stock.entity.Role;
import com.railStock.rail_stock.service.AdminUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;

    }

    @GetMapping("/all")
    public List<AdminUserDTO> getAllUsers() {
        return adminUserService.getAllUsers();
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<Void> changeRole(@PathVariable long id, @RequestParam Role role) {
        adminUserService.changeRole(id, role);
        return ResponseEntity.ok().build();
    }
}
