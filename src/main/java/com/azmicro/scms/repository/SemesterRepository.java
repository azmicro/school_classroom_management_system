package com.azmicro.scms.repository;

import com.azmicro.scms.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    Semester findByWording(String wording);
    List<Semester> findBySchoolYearId(Long id);
}
