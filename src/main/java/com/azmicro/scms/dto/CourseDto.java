package com.azmicro.scms.dto;

import com.azmicro.scms.model.Course;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CourseDto {
    private Long id;
    private Date dateOfCourse;
    private String courseContent;
    private String courseDescription;
    private GradeDto gradeDto;

    public static CourseDto fromEntity(Course course){
        if(course == null){return null;}
        return CourseDto.builder()
                .id(course.getId())
                .dateOfCourse(course.getDateOfCourse())
                .courseContent(course.getCourseContent())
                .courseDescription(course.getCourseDescription())
                .gradeDto(GradeDto.fromEntity(course.getGrade()))
                .build();
    }
    public static Course toEntity(CourseDto courseDto){
        Course course = new Course();
        course.setId(courseDto.getId());
        course.setDateOfCourse(courseDto.getDateOfCourse());
        course.setCourseContent(courseDto.getCourseContent());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setGrade(GradeDto.toEntity(courseDto.getGradeDto()));
        return course;
    }
}
