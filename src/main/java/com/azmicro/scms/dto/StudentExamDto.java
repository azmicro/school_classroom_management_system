package com.azmicro.scms.dto;

import com.azmicro.scms.model.StudentExam;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class StudentExamDto {

    private Long id;
    private Date examDate;
    private Date correctionDate;
    private double mark;
    private StudentDto studentDto;
    private ExamDto examDto;

    public static StudentExamDto fromEntity(StudentExam studentExam){
        if(studentExam==null){return null;}
        return StudentExamDto.builder()
                .id(studentExam.getId())
                .examDate(studentExam.getExamDate())
                .correctionDate(studentExam.getCorrectionDate())
                .mark(studentExam.getMark())
                .studentDto(StudentDto.fromEntity(studentExam.getStudent()))
                .examDto(ExamDto.fromEntity(studentExam.getExam()))
                .build();
    }
    public static StudentExam toEntity(StudentExamDto studentExamDto){
        StudentExam studentExam = new StudentExam();
        studentExam.setId(studentExamDto.getId());
        studentExam.setExamDate(studentExamDto.getExamDate());
        studentExam.setCorrectionDate(studentExamDto.getCorrectionDate());
        studentExam.setMark(studentExamDto.getMark());
        studentExam.setStudent(StudentDto.toEntity(studentExamDto.getStudentDto()));
        studentExam.setExam(ExamDto.toEntity(studentExamDto.getExamDto()));
        return studentExam;
    }
}
