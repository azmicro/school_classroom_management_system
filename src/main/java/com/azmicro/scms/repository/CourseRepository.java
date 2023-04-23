package com.azmicro.scms.repository;

import com.azmicro.scms.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByDateOfCourseBetween(Date startDate, Date endDate);

    List<Course> findByGradeId(Long gradeId);

    List<Course> findByCourseContentContainingIgnoreCase(String keyword);

    Course findByCourseDescription(String courseDescription);
}
