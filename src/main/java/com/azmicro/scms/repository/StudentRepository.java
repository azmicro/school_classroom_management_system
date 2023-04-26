package com.azmicro.scms.repository;

import com.azmicro.scms.model.Grade;
import com.azmicro.scms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByCode(String code);
    Student findByLastNameFr(String lastNameFr);
    Student findByLastNameAr(String lastNameAr);
    Student findByFirstNameFr(String firstNameFr);
    Student findByFirstNameAr(String firstNameAr);
    List<Student> findAll();
    List<Student> findByLastNameFrContainingIgnoreCase(String lastNameFr);
    List<Student> findByFirstNameFrContainingIgnoreCase(String firstNameFr);
    List<Student> findByGradeId(Long gradeId);
    List<Student> findByDateOfBirth(Date dateOfBirth);
    List<Student> findByLastNameFrAndFirstNameFr(String lastNameFr, String firstNameFr);
}
