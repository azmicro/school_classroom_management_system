package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.RoleDto;
import com.azmicro.scms.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface RoleApi {
    @PostMapping(value = APP_ROOT+"/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto save(@RequestBody RoleDto roleDto);
    @DeleteMapping(value = APP_ROOT+"/roles/delete/{roleId}")
    void delete(@PathVariable("roleId") Long id);
    @GetMapping(value = APP_ROOT+"/roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto findById(@PathVariable("roleId") Long id);
    @GetMapping(value = APP_ROOT+"/roles/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto findByName(@PathVariable("roleName") String name);
    @GetMapping(value = APP_ROOT+"/roles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoleDto> findAll();
}
