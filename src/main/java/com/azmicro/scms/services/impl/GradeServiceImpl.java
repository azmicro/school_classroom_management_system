package com.azmicro.scms.services.impl;

import com.azmicro.scms.dto.GradeDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.Grade;
import com.azmicro.scms.repository.GradeRepository;
import com.azmicro.scms.services.GradeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class GradeServiceImpl implements GradeService{

    private final GradeRepository gradeRepository;

    @Override
    public List<GradeDto> findAll() {
        List<Grade> grades = gradeRepository.findAll();
        return grades.stream().map(GradeDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public GradeDto findById(Long id) throws EntityNotFoundException {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Could not find grade with id: " + id));
        return GradeDto.fromEntity(grade);
    }

    @Override
    public GradeDto save(GradeDto gradeDto) throws InvalidEntityException {
        if (gradeDto == null) {
            throw new InvalidEntityException("Grade cannot be null.");
        }
        Grade grade = GradeDto.toEntity(gradeDto);
        grade = gradeRepository.save(grade);
        return GradeDto.fromEntity(grade);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        if (!gradeRepository.existsById(id)) {
            throw new EntityNotFoundException("Could not find grade with id: " + id);
        }
        gradeRepository.deleteById(id);
    }

    @Override
    public List<GradeDto> findByHighSchoolId(Long highSchoolId) {
        List<Grade> grades = gradeRepository.findByHighSchoolId(highSchoolId);
        return grades.stream().map(GradeDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<GradeDto> findBySchoolYearId(Long schoolYearId) {
        List<Grade> grades = gradeRepository.findBySchoolYearId(schoolYearId);
        return grades.stream().map(GradeDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<GradeDto> findByWordingContainingIgnoreCase(String keyword) {
        List<Grade> grades = gradeRepository.findByWordingContainingIgnoreCase(keyword);
        if (grades.isEmpty()) {
            throw new EntityNotFoundException("No grade containing keyword: " + keyword + " was found.");
        }
        return grades.stream()
                .map(GradeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<GradeDto> findByNumberOfStudentGreaterThan(int numberOfStudent) throws InvalidEntityException, EntityNotFoundException {
        if (numberOfStudent < 0) {
            throw new InvalidEntityException("Number of students must be a positive integer.");
        }
        List<Grade> grades = gradeRepository.findByNumberOfStudentGreaterThan(numberOfStudent);
        if (grades.isEmpty()) {
            throw new EntityNotFoundException("No grade found with more than " + numberOfStudent + " students.");
        }
        return grades.stream()
                .map(GradeDto::fromEntity)
                .collect(Collectors.toList());
    }

}
