package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface StudentApi {
    @GetMapping(value = APP_ROOT+"/students/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findById(@PathVariable("idStudent") Long id);

    @GetMapping(value = APP_ROOT+"/students/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByCode(@PathVariable("code") String code);

    @GetMapping(value = APP_ROOT+"/students/{lastNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByLastNameFr(@PathVariable("lastNameFr") String lastNameFr);

    @GetMapping(value = APP_ROOT+"/students/{lastNameAr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByLastNameAr(@PathVariable("lastNameAr") String lastNameAr);

    @GetMapping(value = APP_ROOT+"/students/{firstNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByFirstNameFr(@PathVariable("firstNameFr") String firstNameFr);

    @GetMapping(value = APP_ROOT+"/students/{firstNameAr}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentDto findByFirstNameAr(@PathVariable("firstNameAr") String firstNameAr);

    @GetMapping(value = APP_ROOT+"/students/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findAll();

    @GetMapping(value = APP_ROOT+"/students/lastname/{lastNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByLastNameFrContaining(@PathVariable("lastNameFr") String lastNameFr);

    @GetMapping(value = APP_ROOT+"/students/firstname/{firstNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByFirstNameFrContaining(@PathVariable("firstNameFr") String firstNameFr);

    @GetMapping(value = APP_ROOT+"/students/grade/{gradeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByGradeId(@PathVariable("gradeId") Long gradeId);

    @GetMapping(value = APP_ROOT+"/students/{dateOfBirth}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByDateOfBirth(@PathVariable("dateOfBirth") Date dateOfBirth);

    @GetMapping(value = APP_ROOT+"/students/{lastNameFr}/{firstNameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentDto> findByLastNameFrAndFirstNameFr(@PathVariable("lastNameFr") String lastNameFr, @PathVariable("firstNameFr") String firstNameFr);

    @PostMapping(value = APP_ROOT+"/students/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    StudentDto save(@RequestBody StudentDto studentDto);


    @DeleteMapping(value = APP_ROOT+"/students/delete/{studentId}")
    void deleteById(@PathVariable("studentId") Long id);
}
