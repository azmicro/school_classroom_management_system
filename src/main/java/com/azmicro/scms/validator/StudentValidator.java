package com.azmicro.scms.validator;

import com.azmicro.scms.dto.StudentDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentValidator {

    public static List<String> validate(StudentDto dto) {
        List<String> errors = new ArrayList<>();

        if (dto == null) {
            errors.add("StudentDto is null");
            return errors;
        }

        if (!StringUtils.hasLength(dto.getCode())) {
            errors.add("Code is required");
        }



        if (!StringUtils.hasLength(dto.getLastNameAr())) {
            errors.add("Last name (AR) is required");
        }



        if (!StringUtils.hasLength(dto.getFirstNameAr())) {
            errors.add("First name (AR) is required");
        }

        if (dto.getGender() == null) {
            errors.add("Gender is required");
        }


        if (dto.getGradeDto() == null) {
            errors.add("Grade is required");
        } else {
            List<String> gradeErrors = GradeValidator.validate(dto.getGradeDto());
            errors.addAll(gradeErrors);
        }

        return errors;
    }
}
