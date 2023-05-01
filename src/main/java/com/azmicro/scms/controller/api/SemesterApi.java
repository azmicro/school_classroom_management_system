package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface SemesterApi {
    @GetMapping(value = APP_ROOT + "/semesters/{idSemester}", produces = {MediaType.APPLICATION_JSON_VALUE})
    SemesterDto findById(@PathVariable("idSemester") Long id);
    @GetMapping(value = APP_ROOT + "/semesters/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<SemesterDto> findAll();

    @PostMapping(value = APP_ROOT + "/semesters/create", produces = {MediaType.APPLICATION_JSON_VALUE})
    SemesterDto save(SemesterDto semesterDto);
    @DeleteMapping(value = APP_ROOT +"/semesters/{idSemester}")
    void delete(@PathVariable("idSemester") Long id);

    @GetMapping(value = APP_ROOT + "/semesters/schoolyear/{idSchoolYear}", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<SemesterDto> findBySchoolYearId(@PathVariable("idSchoolYear") Long id);
}
