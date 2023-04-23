package com.azmicro.scms.validator;

import com.azmicro.scms.dto.ExamDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ExamValidator {
    public static List<String> validate(ExamDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("ExamDto is null");
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
