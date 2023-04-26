package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.CourseDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.ErrorCodes;
import com.azmicro.scms.model.Course;
import com.azmicro.scms.repository.CourseRepository;
import com.azmicro.scms.services.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;

    @Override
    public CourseDto save(CourseDto courseDto) {
        Course course = CourseDto.toEntity(courseDto);
        course = courseRepository.save(course);
        return CourseDto.fromEntity(course);
    }

    @Override
    public CourseDto findById(Long id) {
        if(id==null) {
            log.error("Course id is null");
            return null;
        }
        Optional<Course> course = courseRepository.findById(id);
        return course.map(CourseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id, ErrorCodes.COURSE_NOT_FOUND));
    }

    @Override
    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream()
                .map(CourseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if(id==null) {
            log.error("Course id is null");
            return;
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseDto> findByDateOfCourseBetween(Date startDate, Date endDate) {
        return courseRepository.findByDateOfCourseBetween(startDate, endDate).stream()
                .map(CourseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> findByGradeId(Long gradeId) {
        return courseRepository.findByGradeId(gradeId).stream()
                .map(CourseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> findByCourseContentContainingIgnoreCase(String keyword) {
        return courseRepository.findByCourseContentContainingIgnoreCase(keyword).stream()
                .map(CourseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto findByCourseDescription(String courseDescription) {
        Course course = courseRepository.findByCourseDescription(courseDescription);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with description: " + courseDescription, ErrorCodes.COURSE_NOT_FOUND);
        }
        return CourseDto.fromEntity(course);
    }
}
