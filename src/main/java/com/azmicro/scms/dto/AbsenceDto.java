package com.azmicro.scms.dto;

import com.azmicro.scms.model.Absence;
import com.azmicro.scms.model.Semester;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AbsenceDto {
    Long id;
    private String description;
    private String dateOfAbsence;
    private SemesterDto semesterDto;
    private StudentDto studentDto;
    public static AbsenceDto fromEntity(Absence absence){
        if(absence == null){return null;}
        return AbsenceDto.builder()
                .id(absence.getId())
                .description(absence.getDescription())
                .dateOfAbsence(absence.getDateOfAbsence())
                .semesterDto(SemesterDto.fromEntity(absence.getSemester()))
                .studentDto(StudentDto.fromEntity(absence.getStudent()))
                .build();
    }
    public static Absence toEntity(AbsenceDto absenceDto){
        Absence absence = new Absence();
        absence.setId(absenceDto.getId());
        absence.setDescription(absenceDto.getDescription());
        absence.setDateOfAbsence(absenceDto.getDateOfAbsence());
        absence.setSemester(SemesterDto.toEntity(absenceDto.getSemesterDto()));
        absence.setStudent(StudentDto.toEntity(absenceDto.getStudentDto()));
        return absence;

    }
}
