package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.AbsenceDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

@Api(APP_ROOT+"/absences")
public interface AbsenceApi {
    @PostMapping(value = APP_ROOT+"/absences/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create absences", notes = "this method creates a new absences or modified", response = AbsenceDto.class)
    @ApiResponse(code = 200, message = "Successfully; absence created")
    AbsenceDto save(@RequestBody AbsenceDto dto);
    @DeleteMapping(value = APP_ROOT+"/absences/delete/{idAbsence}")
    void delete(@PathVariable("idAbsence") Long id);
    @GetMapping(value = APP_ROOT+"/absences/{idAbsence}", produces = MediaType.APPLICATION_JSON_VALUE)
    AbsenceDto findById(@PathVariable("idAbsence") Long id);
    @GetMapping(value = APP_ROOT+"/absences/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AbsenceDto> findAll();
    @GetMapping(value = APP_ROOT+"/absences/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AbsenceDto> findByStudentId(@PathVariable("idStudent") Long studentId);
    @GetMapping(value = APP_ROOT+"/absences/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AbsenceDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);
    @GetMapping(value = APP_ROOT+"/absences/student/{idStudent}/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<AbsenceDto> findByStudentAndSemester(@PathVariable("idStudent") Long studentId, @PathVariable("idSemester") Long semesterId);


}
