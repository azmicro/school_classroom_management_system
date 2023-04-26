package com.azmicro.scms.services.impl;

import com.azmicro.scms.dto.AbsenceDto;
import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.ErrorCodes;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.model.Absence;
import com.azmicro.scms.model.Semester;
import com.azmicro.scms.model.Student;
import com.azmicro.scms.repository.AbsenceRepository;
import com.azmicro.scms.repository.SemesterRepository;
import com.azmicro.scms.repository.StudentRepository;
import com.azmicro.scms.services.AbsenceService;
import com.azmicro.scms.validator.AbsenceValidator;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class AbsenceServiceImpl implements AbsenceService {

    private AbsenceRepository absenceRepository;
    private SemesterRepository semesterRepository;
    private StudentRepository studentRepository;
    @Override
    public AbsenceDto findById(Long id) {
        if(id == null) {
            log.error("Absence id is null");
            return null;
        }
        Optional<Absence> absence = absenceRepository.findById(id);
        return absence.map(AbsenceDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Absence was not found", ErrorCodes.ABSENCE_NOT_FOUND));
    }

    @Override
    public List<AbsenceDto> findAll() {
        return absenceRepository.findAll().stream()
                .map(AbsenceDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AbsenceDto save(AbsenceDto dto) {
        List<String> errors = AbsenceValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Absence was not valid");
            throw new InvalidEntityException("Absence was not valid", ErrorCodes.ABSENCE_NOT_INVALID, errors);
        }
        Optional<Semester> semester = semesterRepository.findById(dto.getStudentDto().getId());
        if(semester.isEmpty()) {
            log.error("Semester was not found");
            throw new InvalidEntityException("Semester was not found", ErrorCodes.SEMESTER_NOT_FOUND);
        }
        Optional<Student> student = studentRepository.findById(dto.getStudentDto().getId());
        if(student.isEmpty()) {
            log.error("Student was not found");
            throw new InvalidEntityException("Student was not found", ErrorCodes.STUDENT_NOT_FOUND);
        }
        return AbsenceDto.fromEntity(absenceRepository.save(AbsenceDto.toEntity(dto)));
    }

    @Override
    public void deleteById(Long id) {
        if(id == null) {
            log.error("Absence id is null");
            return;
        }
        Optional<Absence> optionalAbsence = absenceRepository.findById(id);
        if(optionalAbsence.isPresent()) {
            absenceRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Absence was not found", ErrorCodes.ABSENCE_NOT_FOUND);
        }
    }

    @Override
    public List<AbsenceDto> findByStudentId(Long studentId) {

        if(studentId == null) {
            log.error("Student id is null");
            return Collections.emptyList();
        }
        List<Absence> absences = absenceRepository.findByStudentId(studentId);
        return absences.stream()
                .map(AbsenceDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDto> findBySemesterId(Long semesterId) {

        if(semesterId == null) {
            log.error("Semester id is null");
            return null;
        }
        List<Absence> absences = absenceRepository.findBySemesterId(semesterId);
        return absences.stream().map(AbsenceDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<AbsenceDto> findByStudentAndSemester(Long studentId, Long semesterId) {
        List<Absence> absences = absenceRepository.findByStudentIdAndSemesterId(
                studentId, semesterId
        );
        return absences.stream()
                .map(AbsenceDto::fromEntity)
                .collect(Collectors.toList());
    }
}
