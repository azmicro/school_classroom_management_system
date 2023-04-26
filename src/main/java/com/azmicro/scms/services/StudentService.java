package com.azmicro.scms.services;
import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.Date;
import java.util.List;
public interface StudentService {
        StudentDto findById(Long id) throws EntityNotFoundException;

        StudentDto findByCode(String code) throws EntityNotFoundException;

        StudentDto findByLastNameFr(String lastNameFr) throws EntityNotFoundException;

        StudentDto findByLastNameAr(String lastNameAr) throws EntityNotFoundException;

        StudentDto findByFirstNameFr(String firstNameFr) throws EntityNotFoundException;

        StudentDto findByFirstNameAr(String firstNameAr) throws EntityNotFoundException;

        List<StudentDto> findAll();

        List<StudentDto> findByLastNameFrContaining(String lastNameFr);

        List<StudentDto> findByFirstNameFrContaining(String firstNameFr);

        List<StudentDto> findByGradeId(Long gradeId);

        List<StudentDto> findByDateOfBirth(Date dateOfBirth);

        List<StudentDto> findByLastNameFrAndFirstNameFr(String lastNameFr, String firstNameFr);

        StudentDto save(StudentDto studentDto) throws InvalidEntityException;

        StudentDto update(StudentDto studentDto) throws InvalidEntityException, EntityNotFoundException;

        void deleteById(Long id) throws EntityNotFoundException, InvalidOperationException;

}

