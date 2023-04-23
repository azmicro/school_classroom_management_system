package com.azmicro.scms.dto;

import com.azmicro.scms.model.Semester;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class SemesterDto {

    private Long id;
    private String wording;
    private Date startDate;
    private Date endDate;
    private SchoolYearDto schoolYearDto;

    public static SemesterDto fromEntity(Semester semester){
        if(semester == null) return null;
        return builder()
                .id(semester.getId())
                .wording(semester.getWording())
                .startDate(semester.getStartDate())
                .endDate(semester.getEndDate())
                .schoolYearDto(SchoolYearDto.fromEntity(semester.getSchoolYear()))
                .build();
    }
    public static Semester toEntity(SemesterDto semesterDto){
        Semester semester = new Semester();
        semester.setId(semesterDto.getId());
        semester.setWording(semesterDto.getWording());
        semester.setStartDate(semesterDto.getStartDate());
        semester.setEndDate(semesterDto.getEndDate());
        semester.setSchoolYear(SchoolYearDto.toEntity(semesterDto.getSchoolYearDto()));
        return semester;
    }
}
