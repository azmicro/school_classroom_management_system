package com.azmicro.scms.services;
import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.List;
public interface SemesterService {
    SemesterDto findById(Long id) throws EntityNotFoundException;

    List<SemesterDto> findAll();

    SemesterDto save(SemesterDto semesterDto) throws InvalidEntityException;

    SemesterDto update(SemesterDto semesterDto) throws InvalidEntityException, EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException, InvalidOperationException;

    List<SemesterDto> findBySchoolYearId(Long id);
}
