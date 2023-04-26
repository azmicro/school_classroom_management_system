package com.azmicro.scms.services;

import com.azmicro.scms.dto.AbsenceDto;
import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.dto.StudentDto;

import java.util.List;

public interface AbsenceService {
    AbsenceDto findById(Long id);
    List<AbsenceDto> findAll();
    AbsenceDto save(AbsenceDto absenceDto);
    void deleteById(Long id);
    List<AbsenceDto> findByStudentId(Long studentId);
    List<AbsenceDto> findBySemesterId(Long semesterId);
    List<AbsenceDto> findByStudentAndSemester(Long studentId, Long semesterId);
}
