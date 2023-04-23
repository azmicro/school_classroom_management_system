package com.azmicro.scms.validator;

import com.azmicro.scms.dto.AbsenceDto;
import com.azmicro.scms.model.Absence;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AbsenceValidator {
    public static List<String> validate(AbsenceDto dto){
        List<String> errors = new ArrayList<>();
        if(dto==null){
            errors.add("Veuillez renseigner une description de l'absence");
            errors.add("Veuillez renseigner une semestre");
            errors.add("Veuillez renseigner un  élève");
            errors.add("veulliez renseigner une date");
            return errors;
        }

        if(dto.getSemesterDto()==null){
            errors.add("Veuillez renseigner une semestre");
        }
        if(dto.getStudentDto()==null){
            errors.add("Veuillez renseigner un élève");
        }
        if(dto.getDateOfAbsence()==null){
            errors.add("Veuillez renseigner une date d'absence");
        }
        return errors;
    }
}
