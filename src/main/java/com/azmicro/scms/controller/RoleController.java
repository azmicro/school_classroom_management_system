package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.RoleApi;
import com.azmicro.scms.dto.RoleDto;
import com.azmicro.scms.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class RoleController implements RoleApi {
    private RoleService roleService;

    @Override
    public RoleDto save(RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @Override
    public void delete(Long id) {
        roleService.delete(id);
    }

    @Override
    public RoleDto findById(Long id) {
        return roleService.findById(id);
    }

    @Override
    public RoleDto findByName(String name) {
        return roleService.findByName(name);
    }

    @Override
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }
}
