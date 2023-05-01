package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.GradeDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface GradeApi {
    @GetMapping(value = APP_ROOT+"/grades/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GradeDto> findAll();

    @GetMapping(value = APP_ROOT+"/grades/{idGrade}", produces = MediaType.APPLICATION_JSON_VALUE)
    GradeDto findById(@PathVariable("idGrade") Long id) throws EntityNotFoundException;

    @PostMapping(value = APP_ROOT+"/grades/create",consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    GradeDto save(@RequestBody GradeDto gradeDto);

    @DeleteMapping(value = APP_ROOT+"/grades/delete/{idGrade}")
    void deleteById(@PathVariable("idGrade") Long id);

    @GetMapping(value=APP_ROOT+"/grades/highSchool/{idHighSchool}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GradeDto> findByHighSchoolId(@PathVariable("idHighSchool") Long highSchoolId);

    @GetMapping(value=APP_ROOT+"/grades/schoolYear/{idSchoolYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GradeDto> findBySchoolYearId(@PathVariable("idSchoolYear") Long schoolYearId);

    @GetMapping(value=APP_ROOT+"/grades/wordingContainingIgnoreCase/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GradeDto> findByWordingContainingIgnoreCase(@PathVariable("keyword") String keyword);

    @GetMapping(value=APP_ROOT+"/grades/numberOfStudentGreaterThan/{numberOfStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<GradeDto> findByNumberOfStudentGreaterThan(@PathVariable("numberOfStudent") int numberOfStudent);




}
