package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.ExamDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface ExamApi {
    @GetMapping(value = APP_ROOT+"/exams/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<ExamDto> findAll();

    @GetMapping(value = APP_ROOT+"/exams/{idExam}", produces = MediaType.APPLICATION_JSON_VALUE)
    ExamDto findById(@PathVariable("idExam") Long id);

    @PostMapping(value = APP_ROOT+"/exams/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ExamDto save(@RequestBody ExamDto examDto);

    @DeleteMapping(value = APP_ROOT+"/exams/delete/{idExam}")
    void deleteById(@PathVariable("idExam") Long id);

    @GetMapping(value = APP_ROOT+"/exams/semester/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExamDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);

    @GetMapping(value = APP_ROOT+"/exams/exam/{keywords}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ExamDto> findByWordingContainingIgnoreCase(@PathVariable("keywords") String keyword);
}
