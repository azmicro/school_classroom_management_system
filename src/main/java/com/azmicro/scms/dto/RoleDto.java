package com.azmicro.scms.dto;

import com.azmicro.scms.model.Role;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleDto {

    private Long id;
    private String name;

    public static RoleDto fromEntity(Role role){
        if(role == null) return null;
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        Role role = new Role();
        role.setId(roleDto.getId());
        role.setName(roleDto.getName());
        return role;
    }
}
