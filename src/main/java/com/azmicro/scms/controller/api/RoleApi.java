package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.RoleDto;
import com.azmicro.scms.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface RoleApi {
    @Operation(summary = "Create a new role", description = "Create a new role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = APP_ROOT+"/roles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto save(@RequestBody RoleDto roleDto);

    @Operation(summary = "Delete a role by id", description = "Delete a role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid role id supplied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = APP_ROOT+"/roles/delete/{roleId}")
    void delete(@PathVariable("roleId") Long id);

    @Operation(summary = "Find a role by id", description = "Find a role by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found"),
            @ApiResponse(responseCode = "400", description = "Invalid role id supplied"),
            @ApiResponse(responseCode = "404", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/roles/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto findById(@PathVariable("roleId") Long id);

    @Operation(summary = "Find a role by name", description = "Find a role by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role found"),
            @ApiResponse(responseCode = "400", description = "Role not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/roles/{roleName}", produces = MediaType.APPLICATION_JSON_VALUE)
    RoleDto findByName(@PathVariable("roleName") String name);

    @Operation(summary = "Get all roles", description = "Get all roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = APP_ROOT+"/roles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<RoleDto> findAll();

}
