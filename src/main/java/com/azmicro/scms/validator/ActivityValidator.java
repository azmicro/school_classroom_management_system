package com.azmicro.scms.validator;

import com.azmicro.scms.dto.ActivityDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ActivityValidator {
    public static List<String> validate(ActivityDto dto){
        List<String> errors = new ArrayList<String>();
        if(dto==null){
            errors.add("ActivityDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getWording())){
            errors.add("Wording is empty");
        }
        if(dto.getSemesterDto()==null){
            errors.add("SemesterDto is null");
        }
        return errors;
    }
}
