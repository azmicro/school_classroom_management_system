package com.azmicro.scms.services;

import com.azmicro.scms.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto save(RoleDto roleDto);
    void delete(Long id);
    RoleDto findById(Long id);
    RoleDto findByName(String name);
    List<RoleDto> findAll();

}
