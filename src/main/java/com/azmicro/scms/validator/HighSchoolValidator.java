package com.azmicro.scms.validator;

import com.azmicro.scms.dto.HighSchoolDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class HighSchoolValidator {
    public static List<String> validate(HighSchoolDto dto){
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("HighSchoolDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getNameAr())){
            errors.add("NameAr is empty");
        }
        return errors;
    }
}
