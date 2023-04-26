package com.azmicro.scms.services;
import com.azmicro.scms.dto.ExamDto;

import java.util.List;
public interface ExamService {
    List<ExamDto> findAll();

    ExamDto findById(Long id);

    ExamDto save(ExamDto examDto);

    void deleteById(Long id);

    List<ExamDto> findBySemesterId(Long semesterId);

    List<ExamDto> findByWordingContainingIgnoreCase(String keyword);
}
