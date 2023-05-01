package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.StudentExamApi;
import com.azmicro.scms.dto.StudentExamDto;
import com.azmicro.scms.services.StudentExamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class StudentExamController implements StudentExamApi {
    private StudentExamService studentExamService;

    @Override
    public StudentExamDto findById(Long id) {
        return studentExamService.findById(id);
    }

    @Override
    public List<StudentExamDto> findAll() {
        return studentExamService.findAll();
    }

    @Override
    public List<StudentExamDto> findByStudentId(Long studentId) {
        return studentExamService.findByStudentId(studentId);
    }

    @Override
    public List<StudentExamDto> findByExamId(Long examId) {
        return studentExamService.findByExamId(examId);
    }

    @Override
    public List<StudentExamDto> findByExamIdAndStudentId(Long examId, Long studentId) {
        return studentExamService.findByExamIdAndStudentId(examId, studentId);
    }

    @Override
    public List<StudentExamDto> findByExamDate(String examDate) {
        return studentExamService.findByExamDate(examDate);
    }

    @Override
    public List<StudentExamDto> findByCorrectionDate(String correctionDate) {
        return studentExamService.findByCorrectionDate(correctionDate);
    }

    @Override
    public List<StudentExamDto> findByMark(double mark) {
        return studentExamService.findByMark(mark);
    }

    @Override
    public StudentExamDto save(StudentExamDto studentExamDto) {
        return studentExamService.save(studentExamDto);
    }

    @Override
    public void delete(Long id) {
        studentExamService.delete(id);
    }
}
