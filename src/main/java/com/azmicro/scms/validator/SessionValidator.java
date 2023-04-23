package com.azmicro.scms.validator;

import com.azmicro.scms.dto.SessionDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SessionValidator {
    public static List<String> validate(SessionDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("SessionDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getDay())){
            errors.add("Day is empty");
        }
        if(!StringUtils.hasLength(dto.getHourStart())){
            errors.add("HourStart is empty");
        }
        if(!StringUtils.hasLength(dto.getHourEnd())){
            errors.add("HourEnd is empty");
        }
        if(dto.getGradeDto()==null){
            errors.add("GradeDto is null");
        }
        return errors;
    }
}
