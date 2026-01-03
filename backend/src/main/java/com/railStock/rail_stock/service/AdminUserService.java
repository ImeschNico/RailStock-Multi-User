package com.railStock.rail_stock.service;

import com.railStock.rail_stock.dto.AdminUserDTO;
import com.railStock.rail_stock.entity.AppUser;
import com.railStock.rail_stock.entity.Role;
import com.railStock.rail_stock.mapper.AdminUserMapper;
import com.railStock.rail_stock.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {

    private final AppUserRepository appUserRepository;
    private final AdminUserMapper adminUserMapper;

    public AdminUserService(AppUserRepository appUserRepository, AdminUserMapper adminUserMapper) {
        this.appUserRepository = appUserRepository;
        this.adminUserMapper = adminUserMapper;
    }

    public List<AdminUserDTO> getAllUsers() {
        return appUserRepository.findAll().stream()
                .map(adminUserMapper::toDTO)
                .toList();
    }

    public void changeRole(Long id, Role newRole){
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setRole(newRole);
        appUserRepository.save(user);
    }



}
