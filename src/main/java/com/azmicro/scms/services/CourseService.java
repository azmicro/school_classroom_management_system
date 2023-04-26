package com.azmicro.scms.services;

import com.azmicro.scms.dto.CourseDto;

import java.util.Date;
import java.util.List;

public interface CourseService {
    CourseDto save(CourseDto courseDto);

    CourseDto findById(Long id);

    List<CourseDto> findAll();

    void deleteById(Long id);

    List<CourseDto> findByDateOfCourseBetween(Date startDate, Date endDate);

    List<CourseDto> findByGradeId(Long gradeId);

    List<CourseDto> findByCourseContentContainingIgnoreCase(String keyword);

    CourseDto findByCourseDescription(String courseDescription);
}
