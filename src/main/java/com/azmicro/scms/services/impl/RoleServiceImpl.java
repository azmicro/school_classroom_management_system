package com.azmicro.scms.services.impl;

import com.azmicro.scms.dto.RoleDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.ErrorCodes;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.model.Role;
import com.azmicro.scms.repository.RoleRepository;
import com.azmicro.scms.services.RoleService;
import com.azmicro.scms.validator.RoleValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;


    @Override
    public RoleDto save(RoleDto roleDto) {
        List<String> errors = RoleValidator.validate(roleDto);
        if(!errors.isEmpty()) {
            log.error("Role was invalid");
            throw new InvalidEntityException("Role was invalid", ErrorCodes.ROLE_NOT_INVALID, errors);
        }
        return RoleDto.fromEntity(roleRepository.save(RoleDto.toEntity(roleDto)));
    }

    @Override
    public void delete(Long id) {
        if(id==null) {
            log.error("Role id cannot be null");
            return;
        }
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDto findById(Long id) {
        if(id==null){
            log.error("Role id cannot be null");
            return null;
        }
        Optional<Role> role = roleRepository.findById(id);
        return role.map(RoleDto::fromEntity)
                .orElseThrow(() ->
                        new InvalidEntityException("Role was not found", ErrorCodes.ROLE_NOT_FOUND));
    }

    @Override
    public RoleDto findByName(String name) {
        Optional<Role> optionalRole = roleRepository.findByName(name);
        if (optionalRole.isPresent()) {
            return RoleDto.fromEntity(optionalRole.get());
        } else {
            throw new EntityNotFoundException("Role with name " + name + " not found");
        }
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
    }
}
