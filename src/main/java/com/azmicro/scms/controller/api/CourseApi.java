package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.CourseDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface CourseApi {

    @PostMapping(value =APP_ROOT+"/courses/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CourseDto save(@RequestBody CourseDto courseDto);

    @GetMapping(value =APP_ROOT+"/courses/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    CourseDto findById(@PathVariable("courseId") Long id);

    @GetMapping(value =APP_ROOT+"/courses/all")
    List<CourseDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/courses/delete/{courseId}")
    void deleteById(@PathVariable("courseId") Long id);

    @GetMapping(value =APP_ROOT+"/courses/{startDate}/{endDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CourseDto> findByDateOfCourseBetween(@PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate);

    @GetMapping(value =APP_ROOT+"/courses/grade/{gradeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CourseDto> findByGradeId(@PathVariable("gradeId") Long gradeId);

    @GetMapping(value = APP_ROOT+"/courses/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CourseDto> findByCourseContentContainingIgnoreCase(@PathVariable("keyword") String keyword);

    @GetMapping(value =APP_ROOT+"/courses/{courseDescription}", produces=MediaType.APPLICATION_JSON_VALUE)
    CourseDto findByCourseDescription(@PathVariable("courseDescription") String courseDescription);
}
