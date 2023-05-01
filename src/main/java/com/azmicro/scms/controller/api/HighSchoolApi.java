package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.HighSchoolDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface HighSchoolApi {

    @GetMapping(value = APP_ROOT+"/highSchools/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findAll();

    @GetMapping(value = APP_ROOT+"/highSchools/{highSchoolId}", produces = MediaType.APPLICATION_JSON_VALUE)
    HighSchoolDto findById(@PathVariable("highSchoolId") Long id);

    @PostMapping(value = APP_ROOT+"/highSchools/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    HighSchoolDto save(@RequestBody HighSchoolDto highSchoolDto);

    @DeleteMapping(value = APP_ROOT+"/highSchools/delete/{highSchoolId}")
    void deleteById(@PathVariable("highSchoolId") Long id);

    @GetMapping(value = APP_ROOT+"/highSchool/{nameAr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findByNameAr(@PathVariable("nameAr") String nameAr);

    @GetMapping(value = APP_ROOT+"/highSchool/{nameFr}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<HighSchoolDto> findByNameFr(@PathVariable("nameFr") String nameFr);

    @GetMapping(value = APP_ROOT+"/highSchool/{address}")
    List<HighSchoolDto> findByAdress(@PathVariable("address") String address);

    @GetMapping(value = APP_ROOT+"/highSchool/grade/{idGrade}")
    List<HighSchoolDto> findByGradeId(@PathVariable("idGrade") Long gradeId);
}
