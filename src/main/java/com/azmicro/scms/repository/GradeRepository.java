package com.azmicro.scms.repository;

import com.azmicro.scms.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByHighSchoolId(Long highSchoolId);

    List<Grade> findBySchoolYearId(Long schoolYearId);

    List<Grade> findByWordingContainingIgnoreCase(String keyword);

    List<Grade> findByNumberOfStudentGreaterThan(int numberOfStudent);
}
