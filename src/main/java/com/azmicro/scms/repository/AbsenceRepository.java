package com.azmicro.scms.repository;

import com.azmicro.scms.model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    List<Absence> findByStudentId(Long studentId);

    List<Absence> findBySemesterId(Long semesterId);

    List<Absence> findByDateOfAbsence(String dateOfAbsence);

    List<Absence> findByStudentIdAndSemesterId(Long studentId, Long semesterId);

    List<Absence> findByStudentIdAndDateOfAbsence(Long studentId, String dateOfAbsence);


    List<Absence> findBySemesterIdAndDateOfAbsence(Long semesterId, String dateOfAbsence);

}
