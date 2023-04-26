package com.azmicro.scms.services;
import com.azmicro.scms.dto.SchoolYearDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.Date;
import java.util.List;
public interface SchoolYearService {
    SchoolYearDto findById(Long id) throws EntityNotFoundException;

    SchoolYearDto findByWording(String wording) throws EntityNotFoundException;

    List<SchoolYearDto> findAll();

    List<SchoolYearDto> findAllByStartDateGreaterThanEqual(Date startDate);

    List<SchoolYearDto> findAllByEndDateLessThanEqual(Date endDate);

    SchoolYearDto save(SchoolYearDto schoolYearDto) throws InvalidEntityException;

    SchoolYearDto update(SchoolYearDto schoolYearDto) throws InvalidEntityException, EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException, InvalidOperationException;
}
