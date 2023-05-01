package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.UserApi;
import com.azmicro.scms.dto.UserDto;
import com.azmicro.scms.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UseController implements UserApi {
    private UserService useService;

    @Override
    public UserDto save(UserDto userDto) {
        return useService.save(userDto);
    }

    @Override
    public void delete(Long id) {
        useService.delete(id);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        return useService.findById(id);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return useService.findByEmail(email);
    }

    @Override
    public List<UserDto> findAll() {
        return useService.findAll();
    }

    @Override
    public List<UserDto> findAllByRole(Long roleId) {
        return useService.findAllByRole(roleId);
    }

    @Override
    public Optional<UserDto> findByEmailAndPassword(String email, String password) {
        return useService.findByEmailAndPassword(email, password);
    }
}
