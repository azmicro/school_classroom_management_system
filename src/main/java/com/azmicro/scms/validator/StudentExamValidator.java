package com.azmicro.scms.validator;

import com.azmicro.scms.dto.StudentExamDto;

import java.util.ArrayList;
import java.util.List;

public class StudentExamValidator {
    public static List<String> validate(StudentExamDto dto){
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("StudentExamDto is null");
            return errors;
        }
        if(dto.getExamDate() == null){
            errors.add("ExamDate is null");
        }
        if(dto.getMark() < 0 || dto.getMark() > 20){
            errors.add("Mark must be between 0 and 20");
        }
        if(dto.getStudentDto() == null){
            errors.add("StudentDto is null");
        }
        if(dto.getExamDto() == null){
            errors.add("ExamDto is null");
        }
        return errors;
    }
}
