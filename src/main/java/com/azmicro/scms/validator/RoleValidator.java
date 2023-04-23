package com.azmicro.scms.validator;

import com.azmicro.scms.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {
    public static List<String> validate(RoleDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("RoleDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getName())){
            errors.add("Role name is empty");
        }
        return errors;
    }
}
