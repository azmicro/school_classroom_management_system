package com.azmicro.scms.repository;

import com.azmicro.scms.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findBySemesterId(Long semesterId);

    List<Exam> findByWordingContainingIgnoreCase(String keyword);
}
