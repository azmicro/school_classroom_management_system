package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.Semester;
import com.azmicro.scms.repository.SchoolYearRepository;
import com.azmicro.scms.repository.SemesterRepository;
import com.azmicro.scms.services.SchoolYearService;
import com.azmicro.scms.services.SemesterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SemesterServiceImpl implements SemesterService {
    private final SemesterRepository semesterRepository;
    private final SchoolYearRepository schoolYearRepository;
    @Override
    public SemesterDto findById(Long id) throws EntityNotFoundException {
        Semester semester = semesterRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Semester not found with id: " + id));
        return SemesterDto.fromEntity(semester);
    }
    @Override
    public List<SemesterDto> findAll() {
        return semesterRepository.findAll().stream()
                .map(SemesterDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public SemesterDto save(SemesterDto semesterDto) throws InvalidEntityException {
        if (semesterDto == null) {
            throw new InvalidEntityException("Semester cannot be null");
        }

        Semester semester = semesterRepository.findByWording(semesterDto.getWording());
        if (semester != null) {
            throw new InvalidEntityException("Semester already exists with wording: " + semesterDto.getWording());
        }

        Semester newSemester = SemesterDto.toEntity(semesterDto);
        newSemester.setId(null); // to ensure that a new semester is created
        return SemesterDto.fromEntity(semesterRepository.save(newSemester));
    }

    @Override
    public SemesterDto update(SemesterDto semesterDto) throws InvalidEntityException, EntityNotFoundException {
        if (semesterDto == null || semesterDto.getId() == null) {
            throw new InvalidEntityException("Semester cannot be null or have null id");
        }
        Semester semester = semesterRepository.findById(semesterDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Semester with id " + semesterDto.getId() + " not found"));
        semester.setWording(semesterDto.getWording());
        semester.setStartDate(semesterDto.getStartDate());
        semester.setEndDate(semesterDto.getEndDate());
        semester.setSchoolYear(schoolYearRepository.findById(semesterDto.getSchoolYearDto().getId())
                .orElseThrow(() -> new EntityNotFoundException("SchoolYear with id " + semesterDto.getSchoolYearDto().getId() + " not found")));
        semester = semesterRepository.save(semester);
        return SemesterDto.fromEntity(semester);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, InvalidOperationException {
        Optional<Semester> optionalSemester = semesterRepository.findById(id);
        if (optionalSemester.isEmpty()) {
            throw new EntityNotFoundException("Semester with id " + id + " not found");
        }

        Semester semester = optionalSemester.get();

        semesterRepository.deleteById(id);
    }

    public List<SemesterDto> findBySchoolYearId(Long id) {
        List<Semester> semesters = semesterRepository.findBySchoolYearId(id);
        return semesters.stream()
                .map(SemesterDto::fromEntity)
                .collect(Collectors.toList());
    }
}
