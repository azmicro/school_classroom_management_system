package com.azmicro.scms.dto;

import com.azmicro.scms.model.SchoolYear;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class SchoolYearDto {

    private Long id;
    private String wording;
    private Date startDate;
    private Date endDate;

    public static SchoolYearDto fromEntity(SchoolYear schoolYear){
        if (schoolYear == null ){
            return null;
        }
        return SchoolYearDto.builder()
                .id(schoolYear.getId())
                .wording(schoolYear.getWording())
                .startDate(schoolYear.getStartDate())
                .endDate(schoolYear.getEndDate())
                .build();
    }

    public static SchoolYear toEntity(SchoolYearDto schoolYearDto){
        SchoolYear schoolYear = new SchoolYear();
        schoolYear.setId(schoolYearDto.getId());
        schoolYear.setWording(schoolYearDto.getWording());
        schoolYear.setStartDate(schoolYearDto.getStartDate());
        schoolYear.setEndDate(schoolYearDto.getEndDate());
        return schoolYear;
    }
}
