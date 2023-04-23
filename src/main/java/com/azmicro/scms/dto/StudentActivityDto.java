package com.azmicro.scms.dto;

import com.azmicro.scms.model.Activity;
import com.azmicro.scms.model.StudentActivity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class StudentActivityDto {

    private Long id;
    private Date dateOfActivity;
    private double mark;
    private StudentDto studentDto;
    private ActivityDto activityDto;
    public static StudentActivityDto fromEntity(StudentActivity studentActivity){
        if (studentActivity==null) return null;
        return StudentActivityDto.builder()
                .id(studentActivity.getId())
                .dateOfActivity(studentActivity.getDateOfActivity())
                .mark(studentActivity.getMark())
                .studentDto(StudentDto.fromEntity(studentActivity.getStudent()))
                .activityDto(ActivityDto.fromEntity(studentActivity.getActivity()))
                .build();
    }
    public static StudentActivity toEntity(StudentActivityDto studentActivityDto){
        StudentActivity studentActivity = new StudentActivity();
        studentActivity.setId(studentActivityDto.getId());
        studentActivity.setDateOfActivity(studentActivityDto.getDateOfActivity());
        studentActivity.setMark(studentActivityDto.getMark());
        studentActivity.setStudent(StudentDto.toEntity(studentActivityDto.getStudentDto()));
        studentActivity.setActivity(ActivityDto.toEntity(studentActivityDto.getActivityDto()));
        return studentActivity;
    }
}
