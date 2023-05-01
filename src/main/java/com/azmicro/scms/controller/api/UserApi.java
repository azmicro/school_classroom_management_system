package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.UserDto;
import com.azmicro.scms.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface UserApi {
    @PostMapping(value = APP_ROOT+"/users/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto save(@RequestBody UserDto userDto);

    @DeleteMapping(value = APP_ROOT + "/users/{id}")
    void delete(@PathVariable("id") Long id);

    @GetMapping(value = APP_ROOT + "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserDto> findById(@PathVariable("id") Long id);

    @GetMapping(value = APP_ROOT + "/users/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserDto> findByEmail(@PathVariable("email") String email);

    @GetMapping(value = APP_ROOT + "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> findAll();

    @GetMapping(value = APP_ROOT + "/users/role/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> findAllByRole(@PathVariable("roleId") Long roleId);

    @GetMapping(value = APP_ROOT + "/users/{email}/{password}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserDto> findByEmailAndPassword(@PathVariable("email") String email, @PathVariable("password") String password);

}
