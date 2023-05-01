package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.ExamApi;
import com.azmicro.scms.dto.ExamDto;
import com.azmicro.scms.services.ExamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ExamController implements ExamApi {

    private ExamService examService;
    @Override
    public List<ExamDto> findAll() {
        return examService.findAll();
    }

    @Override
    public ExamDto findById(Long id) {
        return examService.findById(id);
    }

    @Override
    public ExamDto save(ExamDto examDto) {
        return examService.save(examDto);
    }

    @Override
    public void deleteById(Long id) {
        examService.deleteById(id);
    }

    @Override
    public List<ExamDto> findBySemesterId(Long semesterId) {
        return examService.findBySemesterId(semesterId);
    }

    @Override
    public List<ExamDto> findByWordingContainingIgnoreCase(String keyword) {
        return examService.findByWordingContainingIgnoreCase(keyword);
    }
}
