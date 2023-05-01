package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.StudentActivityDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface StudentActivityApi {

    @PostMapping(value=APP_ROOT+"/studentActivities/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    StudentActivityDto save(@RequestBody StudentActivityDto studentActivityDto);

    @DeleteMapping(value = APP_ROOT+"/studentActivities/delete/{studentActivityId}")
    void delete(@PathVariable("studentActivityId") Long id);
    @GetMapping(value = APP_ROOT+"/studentActivities/{idStudentActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    StudentActivityDto findById(@PathVariable("idStudentActivity") Long id);

    @GetMapping(value = APP_ROOT+"/studentActivity/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentActivityDto> findByStudentId(@PathVariable("idStudent") Long studentId);

    @GetMapping(value = APP_ROOT+"/studentActivity/activity/{idActivity}")
    List<StudentActivityDto> findByActivityId(@PathVariable("idActivity") Long activityId);

    @GetMapping(value = APP_ROOT+"/studentActivity/student/{idStudent}/activity/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentActivityDto> findByStudentIdAndActivityId(@PathVariable("idStudent") Long studentId, @PathVariable("idActivity") Long activityId);
}
