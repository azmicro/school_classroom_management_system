package com.azmicro.scms.services.impl;

import com.azmicro.scms.dto.ExamDto;
import com.azmicro.scms.dto.StudentExamDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.ErrorCodes;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.Exam;
import com.azmicro.scms.model.Student;
import com.azmicro.scms.model.StudentExam;
import com.azmicro.scms.repository.ExamRepository;
import com.azmicro.scms.repository.StudentExamRepository;
import com.azmicro.scms.repository.StudentRepository;
import com.azmicro.scms.services.ExamService;
import com.azmicro.scms.services.StudentExamService;
import com.azmicro.scms.services.StudentService;
import com.azmicro.scms.validator.StudentExamValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class StudentExamServiceImpl implements StudentExamService {

    private final StudentExamRepository studentExamRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    private final ExamService examService;
    @Override
    public StudentExamDto findById(Long id) throws EntityNotFoundException {
        Optional<StudentExam> studentExamOptional = studentExamRepository.findById(id);
        if (studentExamOptional.isPresent()) {
            return StudentExamDto.fromEntity(studentExamOptional.get());
        } else {
            throw new EntityNotFoundException("Aucun examen étudiant trouvé avec l'id : " + id);
        }
    }

    @Override
    public List<StudentExamDto> findAll() {
        List<StudentExam> studentExamList = studentExamRepository.findAll();
        return studentExamList.stream()
                .map(StudentExamDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentExamDto> findByStudentId(Long studentId) {
        List<StudentExam> studentExamList = studentExamRepository.findByStudentId(studentId);
        return studentExamList.stream()
                .map(StudentExamDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentExamDto> findByExamId(Long examId) {
        List<StudentExam> studentExamList = studentExamRepository.findByExamId(examId);
        return studentExamList.stream()
                .map(StudentExamDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public List<StudentExamDto> findByExamIdAndStudentId(Long examId, Long studentId) {
        List<StudentExam> studentExamList = studentExamRepository.findByExamIdAndStudentId(examId, studentId);
        return studentExamList.stream()
                .map(StudentExamDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentExamDto> findByExamDate(String examDate) {
        try {
            // Conversion de la date passée en paramètre en objet Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(examDate);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());

            // Appel à la méthode findByExamDate du repository
            List<StudentExam> studentExams = studentExamRepository.findByExamDate(sqlDate);

            // Conversion des entités en DTOs
            List<StudentExamDto> studentExamDtos = new ArrayList<>();
            for (StudentExam studentExam : studentExams) {
                studentExamDtos.add(StudentExamDto.fromEntity(studentExam));
            }
            return studentExamDtos;

        } catch (ParseException e) {
            log.error("Invalid date format: {}", examDate);
            return Collections.emptyList();
        }
    }

    @Override
    public List<StudentExamDto> findByCorrectionDate(String correctionDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(correctionDate);
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            List<StudentExam> studentExams = studentExamRepository.findByCorrectionDate(sqlDate);
            return studentExams.stream().map(StudentExamDto::fromEntity).collect(Collectors.toList());
        } catch (ParseException e) {
            throw new InvalidEntityException("Invalid date format");
        }
    }

    @Override
    public List<StudentExamDto> findByMark(double mark) {
        List<StudentExam> studentExams = studentExamRepository.findByMark(mark);
        return studentExams.stream()
                .map(StudentExamDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public StudentExamDto save(StudentExamDto studentExamDto) throws InvalidEntityException {
        List<String> errors = StudentExamValidator.validate(studentExamDto);
        if (!errors.isEmpty()) {
            log.error("Exam of student not valid");
            throw new InvalidEntityException("Exam of student not valid", ErrorCodes.STUDENT_EXAM_NOT_INVALID);
        }
        Optional<Student> student = studentRepository.findById(studentExamDto.getStudentDto().getId());
        if(student.isEmpty()) {
            log.error("Student not found");
            throw new InvalidEntityException("Student not found", ErrorCodes.STUDENT_NOT_FOUND);
        }
        Optional<Exam> exam = examRepository.findById(studentExamDto.getExamDto().getId());
        if(exam.isEmpty()) {
            log.error("Exam not found");
            throw new InvalidEntityException("Exam not found", ErrorCodes.EXAM_NOT_FOUND);
        }
        return StudentExamDto.fromEntity(studentExamRepository.save(StudentExamDto.toEntity(studentExamDto)));
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, InvalidOperationException {
        // Vérifier si l'examen de l'étudiant existe dans la base de données
        StudentExam studentExam = studentExamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find student exam with id " + id));

        // Vérifier si l'examen de l'étudiant est déjà corrigé
        if (studentExam.getCorrectionDate() != null) {
            throw new InvalidOperationException("Cannot delete a corrected student exam");
        }

        // Supprimer l'examen de l'étudiant
        studentExamRepository.delete(studentExam);
    }

    @Override
    public StudentExamDto update(StudentExamDto studentExamDto) throws InvalidEntityException, EntityNotFoundException {
        // Vérification que l'objet reçu n'est pas null
        if (studentExamDto == null) {
            throw new InvalidEntityException("StudentExamDto ne doit pas être null");
        }

        // Vérification que l'objet existe dans la base de données
        StudentExam studentExam = studentExamRepository.findById(studentExamDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("StudentExam avec id " + studentExamDto.getId() + " n'existe pas"));

        // Mise à jour des champs
        studentExam.setExamDate(studentExamDto.getExamDate());
        studentExam.setCorrectionDate(studentExamDto.getCorrectionDate());
        studentExam.setMark(studentExamDto.getMark());

        // Enregistrement des modifications dans la base de données
        studentExam = studentExamRepository.save(studentExam);

        // Retour de l'objet mis à jour
        return StudentExamDto.fromEntity(studentExam);
    }
}
