package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.StudentActivityApi;
import com.azmicro.scms.dto.StudentActivityDto;
import com.azmicro.scms.services.StudentActivityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class StudentActivityController implements StudentActivityApi {

    private StudentActivityService studentActivityService;

    @Override
    public StudentActivityDto save(StudentActivityDto studentActivityDto) {
        return studentActivityService.save(studentActivityDto);
    }

    @Override
    public void delete(Long id) {
        studentActivityService.delete(id);
    }

    @Override
    public StudentActivityDto findById(Long id) {
        return studentActivityService.findById(id);
    }

    @Override
    public List<StudentActivityDto> findByStudentId(Long studentId) {
        return studentActivityService.findByStudentId(studentId);
    }

    @Override
    public List<StudentActivityDto> findByActivityId(Long activityId) {
        return studentActivityService.findByActivityId(activityId);
    }

    @Override
    public List<StudentActivityDto> findByStudentIdAndActivityId(Long studentId, Long activityId) {
        return studentActivityService.findByStudentIdAndActivityId(studentId, activityId);
    }
}
