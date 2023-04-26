package com.azmicro.scms.services;
import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.dto.StudentExamDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.List;
public interface StudentExamService {
    StudentExamDto findById(Long id) throws EntityNotFoundException;

    List<StudentExamDto> findAll();

    List<StudentExamDto> findByStudentId(Long studentId);

    List<StudentExamDto> findByExamId(Long examId);

    List<StudentExamDto> findByExamIdAndStudentId(Long examId, Long studentId);

    List<StudentExamDto> findByExamDate(String examDate);

    List<StudentExamDto> findByCorrectionDate(String correctionDate);

    List<StudentExamDto> findByMark(double mark);

    StudentExamDto save(StudentExamDto studentExamDto) throws InvalidEntityException;

    void delete(Long id) throws EntityNotFoundException, InvalidOperationException;

    StudentExamDto update(StudentExamDto studentExamDto) throws InvalidEntityException, EntityNotFoundException;
}
