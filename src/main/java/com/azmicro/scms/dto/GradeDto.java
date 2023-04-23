package com.azmicro.scms.dto;

import com.azmicro.scms.model.Grade;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GradeDto {

    private Long id;
    private String wording;
    private int numberOfStudent;

    private HighSchoolDto highSchoolDto;
    private SchoolYearDto schoolYearDto;


    public static GradeDto fromEntity(Grade grade){
        if(grade==null) return null;
        return GradeDto.builder()
                .id(grade.getId())
                .wording(grade.getWording())
                .numberOfStudent(grade.getNumberOfStudent())
                .highSchoolDto(HighSchoolDto.fromEntity(grade.getHighSchool()))
                .schoolYearDto(SchoolYearDto.fromEntity(grade.getSchoolYear()))
                .build();
    }
    public static Grade toEntity(GradeDto gradeDto){
        Grade grade = new Grade();
        grade.setId(gradeDto.getId());
        grade.setWording(gradeDto.getWording());
        grade.setNumberOfStudent(gradeDto.getNumberOfStudent());
        grade.setHighSchool(HighSchoolDto.toEntity(gradeDto.getHighSchoolDto()));
        grade.setSchoolYear(SchoolYearDto.toEntity(gradeDto.getSchoolYearDto()));
        return grade;
    }
}
