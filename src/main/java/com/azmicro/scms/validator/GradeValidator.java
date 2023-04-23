package com.azmicro.scms.validator;

import com.azmicro.scms.dto.GradeDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GradeValidator {
    public static List<String> validate(GradeDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("GradeDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getWording())){
            errors.add("Wording is empty");
        }
        if(dto.getNumberOfStudent() <= 0){
            errors.add("NumberOfStudent must be greater than 0");
        }
        if(dto.getHighSchoolDto()==null){
            errors.add("HighSchoolDto is null");
        }
        if(dto.getSchoolYearDto()==null){
            errors.add("SchoolYearDto is null");
        }
        return errors;
    }
}
