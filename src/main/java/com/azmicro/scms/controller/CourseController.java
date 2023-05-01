package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.CourseApi;
import com.azmicro.scms.dto.CourseDto;
import com.azmicro.scms.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class CourseController implements CourseApi {
    private CourseService courseService;

    @Override
    public CourseDto save(CourseDto courseDto) {
        return courseService.save(courseDto);
    }

    @Override
    public CourseDto findById(Long id) {
        return courseService.findById(id);
    }

    @Override
    public List<CourseDto> findAll() {
        return courseService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        courseService.deleteById(id);
    }

    @Override
    public List<CourseDto> findByDateOfCourseBetween(Date startDate, Date endDate) {
        return courseService.findByDateOfCourseBetween(startDate, endDate);
    }

    @Override
    public List<CourseDto> findByGradeId(Long gradeId) {
        return courseService.findByGradeId(gradeId);
    }

    @Override
    public List<CourseDto> findByCourseContentContainingIgnoreCase(String keyword) {
        return courseService.findByCourseContentContainingIgnoreCase(keyword);
    }

    @Override
    public CourseDto findByCourseDescription(String courseDescription) {
        return courseService.findByCourseDescription(courseDescription);
    }
}
