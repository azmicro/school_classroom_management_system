package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.GradeApi;
import com.azmicro.scms.dto.GradeDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.services.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class GradeController implements GradeApi {
    private final GradeService gradeService;

    @Override
    public List<GradeDto> findAll() {
        return gradeService.findAll();
    }

    @Override
    public GradeDto findById(Long id) throws EntityNotFoundException {
        return gradeService.findById(id);
    }

    @Override
    public GradeDto save(GradeDto gradeDto) {
        return gradeService.save(gradeDto);
    }

    @Override
    public void deleteById(Long id) {
        gradeService.deleteById(id);
    }

    @Override
    public List<GradeDto> findByHighSchoolId(Long highSchoolId) {
        return gradeService.findByHighSchoolId(highSchoolId);
    }

    @Override
    public List<GradeDto> findBySchoolYearId(Long schoolYearId) {
        return gradeService.findBySchoolYearId(schoolYearId);
    }

    @Override
    public List<GradeDto> findByWordingContainingIgnoreCase(String keyword) {
        return gradeService.findByWordingContainingIgnoreCase(keyword);
    }

    @Override
    public List<GradeDto> findByNumberOfStudentGreaterThan(int numberOfStudent) {
        return gradeService.findByNumberOfStudentGreaterThan(numberOfStudent);
    }
}
