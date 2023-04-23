package com.azmicro.scms.dto;

import com.azmicro.scms.model.Exam;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ExamDto {
    private Long id;
    private String wording;
    private SemesterDto semesterDto;
    public static ExamDto fromEntity(Exam exam){
        if (exam == null) return null;
        return ExamDto.builder()
                .id(exam.getId())
                .wording(exam.getWording())
                .semesterDto(SemesterDto.fromEntity(exam.getSemester()))
                .build();
    }
    public static Exam toEntity(ExamDto examDto){
        Exam exam = new Exam();
        exam.setId(examDto.getId());
        exam.setWording(examDto.getWording());
        exam.setSemester(SemesterDto.toEntity(examDto.getSemesterDto()));
        return exam;
    }
}
