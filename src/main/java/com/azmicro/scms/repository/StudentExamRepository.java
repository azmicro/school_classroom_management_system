package com.azmicro.scms.repository;

import com.azmicro.scms.model.Exam;
import com.azmicro.scms.model.Student;
import com.azmicro.scms.model.StudentExam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface StudentExamRepository extends JpaRepository<StudentExam, Long> {

    List<StudentExam> findByExam(Exam exam);

    List<StudentExam> findByExamIdAndStudentId(Long examId, Long studentId);

    List<StudentExam> findByExamDate(Date examDate);

    List<StudentExam> findByCorrectionDate(Date correctionDate);

    List<StudentExam> findByMark(double mark);

    List<StudentExam> findByStudentId(Long studentId);

    List<StudentExam> findByExamId(Long examId);
}
