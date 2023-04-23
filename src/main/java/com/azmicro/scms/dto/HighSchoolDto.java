package com.azmicro.scms.dto;

import com.azmicro.scms.model.HighSchool;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class HighSchoolDto {
    private Long id;
    private String nameAr;
    private String nameFr;
    private String adress;

    public static HighSchoolDto fromEntity(HighSchool highSchool) {
        if (highSchool == null){
            return null;
        }
        return HighSchoolDto.builder()
                .id(highSchool.getId())
                .nameAr(highSchool.getNameAr())
                .nameFr(highSchool.getNameFr())
                .adress(highSchool.getAdress())
                .build();
    }

    public static HighSchool toEntity(HighSchoolDto highSchoolDto) {
        HighSchool highSchool = new HighSchool();
        highSchool.setId(highSchoolDto.getId());
        highSchool.setNameAr(highSchoolDto.getNameAr());
        highSchool.setNameFr(highSchoolDto.getNameFr());
        highSchool.setAdress(highSchoolDto.getAdress());
        return highSchool;
    }
}
