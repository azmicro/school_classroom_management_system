package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.StudentApi;
import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class StudentController implements StudentApi {
    private StudentService studentService;

    @Override
    public StudentDto findById(Long id) {
        return studentService.findById(id);
    }

    @Override
    public StudentDto findByCode(String code) {
        return studentService.findByCode(code);
    }

    @Override
    public StudentDto findByLastNameFr(String lastNameFr) {
        return studentService.findByLastNameFr(lastNameFr);
    }

    @Override
    public StudentDto findByLastNameAr(String lastNameAr) {
        return studentService.findByLastNameAr(lastNameAr);
    }

    @Override
    public StudentDto findByFirstNameFr(String firstNameFr) {
        return studentService.findByFirstNameFr(firstNameFr);
    }

    @Override
    public StudentDto findByFirstNameAr(String firstNameAr) {
        return studentService.findByFirstNameAr(firstNameAr);
    }

    @Override
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @Override
    public List<StudentDto> findByLastNameFrContaining(String lastNameFr) {
        return studentService.findByLastNameFrContaining(lastNameFr);
    }

    @Override
    public List<StudentDto> findByFirstNameFrContaining(String firstNameFr) {
        return studentService.findByFirstNameFrContaining(firstNameFr);
    }

    @Override
    public List<StudentDto> findByGradeId(Long gradeId) {
        return studentService.findByGradeId(gradeId);
    }

    @Override
    public List<StudentDto> findByDateOfBirth(Date dateOfBirth) {
        return studentService.findByDateOfBirth(dateOfBirth);
    }

    @Override
    public List<StudentDto> findByLastNameFrAndFirstNameFr(String lastNameFr, String firstNameFr) {
        return studentService.findByLastNameFrAndFirstNameFr(lastNameFr, firstNameFr);
    }

    @Override
    public StudentDto save(StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @Override
    public void deleteById(Long id) {
        studentService.deleteById(id);
    }
}
