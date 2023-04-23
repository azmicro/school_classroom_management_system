package com.azmicro.scms.validator;

import com.azmicro.scms.dto.StudentActivityDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentActivityValidator {
    public static List<String> validate(StudentActivityDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("StudentActivityDto is null");
            return errors;
        }
        if(dto.getDateOfActivity()==null){
            errors.add("DateOfActivity is null");
        }
        if(dto.getMark() < 0 || dto.getMark() > 20){
            errors.add("Mark must be between 0 and 20");
        }
        if(dto.getStudentDto() == null){
            errors.add("StudentDto is null");
        }
        if(dto.getActivityDto() == null){
            errors.add("ActivityDto is null");
        }
        return errors;
    }
}
