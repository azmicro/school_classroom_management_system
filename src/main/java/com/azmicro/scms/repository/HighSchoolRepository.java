package com.azmicro.scms.repository;

import com.azmicro.scms.model.HighSchool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface HighSchoolRepository extends JpaRepository<HighSchool, Long> {

    List<HighSchool> findByNameAr(String nameAr);

    List<HighSchool> findByNameFr(String nameFr);

    List<HighSchool> findByAdress(String adress);

    List<HighSchool> findByGrades_Id(Long gradeId);


}
