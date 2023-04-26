package com.azmicro.scms.services;
import com.azmicro.scms.dto.HighSchoolDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;

import java.util.List;
public interface HighSchoolService {
    List<HighSchoolDto> findAll();

    HighSchoolDto findById(Long id) throws EntityNotFoundException;

    HighSchoolDto save(HighSchoolDto highSchoolDto) throws InvalidEntityException;

    void deleteById(Long id) throws EntityNotFoundException;

    List<HighSchoolDto> findByNameAr(String nameAr);

    List<HighSchoolDto> findByNameFr(String nameFr);

    List<HighSchoolDto> findByAdress(String adress);

    List<HighSchoolDto> findByGradeId(Long gradeId);
}
