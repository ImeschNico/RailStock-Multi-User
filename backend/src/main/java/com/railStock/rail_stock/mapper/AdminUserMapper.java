package com.railStock.rail_stock.mapper;

import com.railStock.rail_stock.dto.AdminUserDTO;
import com.railStock.rail_stock.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class AdminUserMapper {

    public AdminUserDTO toDTO(AppUser user) {
        AdminUserDTO dto = new AdminUserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
