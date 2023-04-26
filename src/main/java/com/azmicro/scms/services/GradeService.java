package com.azmicro.scms.services;
import com.azmicro.scms.dto.GradeDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.List;
public interface GradeService {
    List<GradeDto> findAll();

    GradeDto findById(Long id) throws EntityNotFoundException;

    GradeDto save(GradeDto gradeDto) throws InvalidEntityException;

    void deleteById(Long id) throws EntityNotFoundException;

    List<GradeDto> findByHighSchoolId(Long highSchoolId);

    List<GradeDto> findBySchoolYearId(Long schoolYearId);

    List<GradeDto> findByWordingContainingIgnoreCase(String keyword);

    List<GradeDto> findByNumberOfStudentGreaterThan(int numberOfStudent);
}
