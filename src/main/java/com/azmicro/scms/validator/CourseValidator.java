package com.azmicro.scms.validator;

import com.azmicro.scms.dto.CourseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseValidator {
    public static List<String> validate(CourseDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("CourseDto is null");
            return errors;
        }
        if(dto.getDateOfCourse()==null){
            errors.add("DateOfCourse is null");
        }
        if(!StringUtils.hasLength(dto.getCourseContent())){
            errors.add("CourseContent is empty");
        }
        if(!StringUtils.hasLength(dto.getCourseDescription())){
            errors.add("CourseDescription is empty");
        }
        if(dto.getGradeDto()==null){
            errors.add("GradeDto is null");
        }
        return errors;
    }
}
