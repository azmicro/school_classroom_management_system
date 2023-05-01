package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.StudentExamDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface StudentExamApi {
    @GetMapping(value = APP_ROOT + "/studentexams/{idStudentExam}", produces = {MediaType.APPLICATION_JSON_VALUE})
    StudentExamDto findById(@PathVariable("idStudentExam") Long id);

    @GetMapping(value = APP_ROOT + "/studentexams/all", produces = {MediaType.APPLICATION_JSON_VALUE})
    List<StudentExamDto> findAll();

    @GetMapping(value = APP_ROOT + "/studentexams/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByStudentId(@PathVariable("idStudent") Long studentId);

    @GetMapping(value = APP_ROOT + "/studentexams/exam/{idExam}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByExamId(@PathVariable("idExam") Long examId);

    @GetMapping(value = APP_ROOT + "/studentexams/exam/{idExam}/student/{idStudent}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByExamIdAndStudentId(@PathVariable("idExam") Long examId, @PathVariable("idStudent") Long studentId);

    @GetMapping(value = APP_ROOT + "/studentexams/{examDate}", produces =MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByExamDate(@PathVariable("examDate") String examDate);

    @GetMapping(value = APP_ROOT + "/studentexams/{correctionDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<StudentExamDto> findByCorrectionDate(@PathVariable("correctionDate") String correctionDate);

    @GetMapping(value = APP_ROOT + "/studentexams/{mark}")
    List<StudentExamDto> findByMark(@PathVariable("mark") double mark);

    @PostMapping(value = APP_ROOT+"/studentexams/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    StudentExamDto save(@RequestBody StudentExamDto studentExamDto);
    @DeleteMapping(value = APP_ROOT + "/studentexams/delete/{studentExamId}")
    void delete(@PathVariable("studentExamId") Long id);

}
