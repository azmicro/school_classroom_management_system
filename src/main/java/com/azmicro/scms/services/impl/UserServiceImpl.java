package com.azmicro.scms.services.impl;


import com.azmicro.scms.dto.RoleDto;
import com.azmicro.scms.dto.UserDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.User;
import com.azmicro.scms.repository.UserRepository;
import com.azmicro.scms.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        if (userDto == null) {
            throw new InvalidEntityException("User cannot be null");
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            throw new InvalidEntityException("User email cannot be null or empty");
        }

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new InvalidOperationException("User with email " + userDto.getEmail() + " already exists");
        }

        User userToSave = UserDto.toEntity(userDto);
        User savedUser = userRepository.save(userToSave);

        return UserDto.fromEntity(savedUser);
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new InvalidEntityException("User id cannot be null");
        }

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }

        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        if (id == null) {
            throw new InvalidEntityException("User id cannot be null");
        }

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();
        return Optional.of(UserDto.fromEntity(user));
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEntityException("User email cannot be null or empty");
        }

        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();
        return Optional.of(UserDto.fromEntity(user));
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllByRole(Long roleId) {
        if (roleId == null) {
            throw new InvalidEntityException("Role id cannot be null");
        }

        List<User> users = userRepository.findAllByRole(RoleDto.toEntity(RoleDto.builder().id(roleId).build()));
        return users.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findByEmailAndPassword(String email, String password) {
        if (email == null || password == null) {
            throw new InvalidEntityException("Email and password must not be null.");
        }

        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            throw new EntityNotFoundException("User not found with email " + email + " and password " + password);
        }

        if (user.get().isDeleted()) {
            throw new InvalidOperationException("User with email " + email + " and password " + password + " has been deleted.");
        }

        return Optional.of(UserDto.fromEntity(user.get()));
    }

}
