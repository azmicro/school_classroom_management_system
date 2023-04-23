package com.azmicro.scms.validator;

import com.azmicro.scms.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(UserDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("UserDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getFirstName())){
            errors.add("First name is required");
        }
        if(!StringUtils.hasLength(dto.getLastName())){
            errors.add("Last name is required");
        }
        if(!StringUtils.hasLength(dto.getEmail())){
            errors.add("Email is required");
        }
        if(!StringUtils.hasLength(dto.getPassword())){
            errors.add("Password is required");
        }
        if(dto.getRoleDto() == null){
            errors.add("RoleDto is null");
        }
        return errors;
    }
}
