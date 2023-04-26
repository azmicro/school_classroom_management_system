package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.ExamDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.model.Exam;
import com.azmicro.scms.repository.ExamRepository;
import com.azmicro.scms.services.ExamService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class ExamServiceImpl implements ExamService{
    private final ExamRepository examRepository;

    @Override
    public List<ExamDto> findAll() {
        List<Exam> exams = examRepository.findAll();
        if (exams.isEmpty()) {
            log.warn("No exams found in database");
            throw new EntityNotFoundException("No exams found in database");
        }
        return exams.stream().map(ExamDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ExamDto findById(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Exam with id {} not found in database", id);
                    return new EntityNotFoundException(String.format("Exam with id %d not found in database", id));
                });
        return ExamDto.fromEntity(exam);
    }

    @Override
    public ExamDto save(ExamDto examDto) {
        if (examDto.getId() != null) {
            log.warn("Id field must be null when creating a new exam, id {} provided", examDto.getId());
            throw new InvalidEntityException("Id field must be null when creating a new exam");
        }
        Exam exam = ExamDto.toEntity(examDto);
        Exam savedExam = examRepository.save(exam);
        log.info("Exam with id {} saved successfully", savedExam.getId());
        return ExamDto.fromEntity(savedExam);
    }

    @Override
    public void deleteById(Long id) {
        if (!examRepository.existsById(id)) {
            log.warn("Exam with id {} not found in database", id);
            throw new EntityNotFoundException(String.format("Exam with id %d not found in database", id));
        }
        examRepository.deleteById(id);
        log.info("Exam with id {} deleted successfully", id);
    }

    @Override
    public List<ExamDto> findBySemesterId(Long semesterId) {
        List<Exam> exams = examRepository.findBySemesterId(semesterId);
        if (exams.isEmpty()) {
            log.warn("No exams found in database for semester with id {}", semesterId);
            throw new EntityNotFoundException(String.format("No exams found in database for semester with id %d", semesterId));
        }
        return exams.stream().map(ExamDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ExamDto> findByWordingContainingIgnoreCase(String keyword) {
        List<Exam> exams = examRepository.findByWordingContainingIgnoreCase(keyword);
        if (exams.isEmpty()) {
            log.warn("No exams found in database containing keyword '{}'", keyword);
            throw new EntityNotFoundException(String.format("No exams found in database containing keyword '%s'", keyword));
        }
        return exams.stream().map(ExamDto::fromEntity).collect(Collectors.toList());
    }
}
