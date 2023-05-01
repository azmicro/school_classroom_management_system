package com.azmicro.scms.services;

import com.azmicro.scms.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto save(UserDto userDto);

    void delete(Long id);

    Optional<UserDto> findById(Long id);

    Optional<UserDto> findByEmail(String email);

    List<UserDto> findAll();

    List<UserDto> findAllByRole(Long roleId);

    Optional<UserDto> findByEmailAndPassword(String email, String password);
}
