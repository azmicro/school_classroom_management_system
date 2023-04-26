package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.GradeDto;
import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.Student;
import com.azmicro.scms.repository.StudentRepository;
import com.azmicro.scms.services.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Override
    public StudentDto findById(Long id) throws EntityNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(id);
        Student student = studentOptional.orElseThrow(() -> new EntityNotFoundException("Student not found with id " + id));
        return StudentDto.fromEntity(student);
    }

    @Override
    public StudentDto findByCode(String code) throws EntityNotFoundException {
        Student student = studentRepository.findByCode(code);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with code " + code);
        }
        return StudentDto.fromEntity(student);
    }

    @Override
    public StudentDto findByLastNameFr(String lastNameFr) throws EntityNotFoundException {
        Student student = studentRepository.findByLastNameFr(lastNameFr);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with French last name " + lastNameFr);
        }
        return StudentDto.fromEntity(student);
    }

    @Override
    public StudentDto findByLastNameAr(String lastNameAr) throws EntityNotFoundException {
        Student student = studentRepository.findByLastNameAr(lastNameAr);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with Arabic last name " + lastNameAr);
        }
        return StudentDto.fromEntity(student);
    }

    @Override
    public StudentDto findByFirstNameFr(String firstNameFr) throws EntityNotFoundException {
        Student student = studentRepository.findByFirstNameFr(firstNameFr);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with French first name " + firstNameFr);
        }
        return StudentDto.fromEntity(student);
    }

    @Override
    public StudentDto findByFirstNameAr(String firstNameAr) throws EntityNotFoundException {
        Student student = studentRepository.findByFirstNameAr(firstNameAr);
        if (student == null) {
            throw new EntityNotFoundException("Student not found with Arabic first name " + firstNameAr);
        }
        return StudentDto.fromEntity(student);
    }

    @Override
    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(StudentDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findByLastNameFrContaining(String lastNameFr) {
        List<Student> students = studentRepository.findByLastNameFrContainingIgnoreCase(lastNameFr);
        return students.stream().map(StudentDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findByFirstNameFrContaining(String firstNameFr) {
        List<Student> students = studentRepository.findByFirstNameFrContainingIgnoreCase(firstNameFr);
        return students.stream().map(StudentDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findByGradeId(Long gradeId) {
        List<Student> students = studentRepository.findByGradeId(gradeId);
        return students.stream().map(StudentDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findByDateOfBirth(Date dateOfBirth) {
        List<Student> students = studentRepository.findByDateOfBirth(dateOfBirth);
        return students.stream().map(StudentDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> findByLastNameFrAndFirstNameFr(String lastNameFr, String firstNameFr) {
        List<Student> students = studentRepository.findByLastNameFrAndFirstNameFr(lastNameFr, firstNameFr);
        return students.stream().map(StudentDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public StudentDto save(StudentDto studentDto) throws InvalidEntityException {
        if (studentDto.getId() != null) {
            throw new InvalidEntityException("The ID must be null when creating a new student.");
        }

        Student student = StudentDto.toEntity(studentDto);

        try {
            student = studentRepository.save(student);
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidEntityException("One or more fields violate entity constraints.", ex);
        }

        return StudentDto.fromEntity(student);
    }


    @Override
    public StudentDto update(StudentDto studentDto) throws InvalidEntityException, EntityNotFoundException {
        // Vérifier que l'étudiant existe
        Student existingStudent = studentRepository.findById(studentDto.getId()).orElse(null);
        if (existingStudent == null) {
            throw new EntityNotFoundException("Student with id " + studentDto.getId() + " not found.");
        }

        // Vérifier que le code de l'étudiant à mettre à jour n'est pas déjà utilisé par un autre étudiant
        Student studentWithSameCode = studentRepository.findByCode(studentDto.getCode());
        if (studentWithSameCode != null && !studentWithSameCode.getId().equals(studentDto.getId())) {
            throw new InvalidEntityException("Student with code " + studentDto.getCode() + " already exists.");
        }

        // Mettre à jour les informations de l'étudiant existant
        existingStudent.setCode(studentDto.getCode());
        existingStudent.setLastNameFr(studentDto.getLastNameFr());
        existingStudent.setLastNameAr(studentDto.getLastNameAr());
        existingStudent.setFirstNameFr(studentDto.getFirstNameFr());
        existingStudent.setFirstNameAr(studentDto.getFirstNameAr());
        existingStudent.setPhone(studentDto.getPhone());
        existingStudent.setAddress(studentDto.getAddress());
        existingStudent.setGender(studentDto.getGender());
        existingStudent.setDateOfBirth(studentDto.getDateOfBirth());
        existingStudent.setPhoto(studentDto.getPhoto());
        existingStudent.setGrade(GradeDto.toEntity(studentDto.getGradeDto()));

        // Enregistrer les changements dans la base de données
        Student savedStudent = studentRepository.save(existingStudent);

        // Retourner la version mise à jour de l'étudiant
        return StudentDto.fromEntity(savedStudent);
    }


    @Override
    public void deleteById(Long id) throws EntityNotFoundException, InvalidOperationException {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
        try {
            studentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidOperationException("Student with id " + id + " is referenced by other entities and cannot be deleted");
        }
    }

}
