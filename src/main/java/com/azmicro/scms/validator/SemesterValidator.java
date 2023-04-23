package com.azmicro.scms.validator;

import com.azmicro.scms.dto.SemesterDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SemesterValidator {
    public static List<String> validate(SemesterDto dto){
        List<String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("SemesterDto is null");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getWording())){
            errors.add("Wording is empty");
        }
        Date startDate = dto.getStartDate();
        Date endDate = dto.getEndDate();
        if(startDate == null || endDate == null){
            errors.add("Start or End date is null");
            return errors;
        }
        if(startDate.compareTo(endDate) > 0){
            errors.add("End date must be after start date");
        }
        if(dto.getSchoolYearDto() == null){
            errors.add("SchoolYearDto is null");
        } else {
            errors.addAll(SchoolYearValidator.validate(dto.getSchoolYearDto()));
        }
        return errors;
    }
}
