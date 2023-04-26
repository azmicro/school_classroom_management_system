package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.SchoolYearDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.SchoolYear;
import com.azmicro.scms.repository.SchoolYearRepository;
import com.azmicro.scms.services.SchoolYearService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SchoolYearServiceImpl implements SchoolYearService {

    private final SchoolYearRepository schoolYearRepository;
    @Override
    public SchoolYearDto findById(Long id) throws EntityNotFoundException {
        SchoolYear schoolYear = schoolYearRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find School Year with id=" + id));
        return SchoolYearDto.fromEntity(schoolYear);
    }

    @Override
    public SchoolYearDto findByWording(String wording) throws EntityNotFoundException {
        SchoolYear schoolYear = schoolYearRepository.findByWording(wording);
        if (schoolYear == null) {
            throw new EntityNotFoundException("Cannot find School Year with wording=" + wording);
        }
        return SchoolYearDto.fromEntity(schoolYear);
    }

    @Override
    public List<SchoolYearDto> findAll() {
        List<SchoolYear> schoolYears = schoolYearRepository.findAll();
        return schoolYears.stream().map(SchoolYearDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<SchoolYearDto> findAllByStartDateGreaterThanEqual(Date startDate) {
        List<SchoolYear> schoolYears = schoolYearRepository.findAllByStartDateGreaterThanEqualOrderByStartDateAsc(startDate);
        return schoolYears.stream().map(SchoolYearDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<SchoolYearDto> findAllByEndDateLessThanEqual(Date endDate) {
        List<SchoolYear> schoolYears = schoolYearRepository.findAllByEndDateLessThanEqualOrderByEndDateAsc(endDate);
        return schoolYears.stream().map(SchoolYearDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public SchoolYearDto save(SchoolYearDto schoolYearDto) throws InvalidEntityException {
        if (schoolYearDto == null) {
            throw new InvalidEntityException("SchoolYear cannot be null");
        }
        if (StringUtils.isBlank(schoolYearDto.getWording())) {
            throw new InvalidEntityException("SchoolYear wording cannot be empty");
        }
        if (schoolYearDto.getStartDate() == null || schoolYearDto.getEndDate() == null) {
            throw new InvalidEntityException("SchoolYear start and end dates cannot be null");
        }
        SchoolYear schoolYear = SchoolYearDto.toEntity(schoolYearDto);
        schoolYear = schoolYearRepository.save(schoolYear);
        return SchoolYearDto.fromEntity(schoolYear);
    }

    @Override
    public SchoolYearDto update(SchoolYearDto schoolYearDto) throws InvalidEntityException, EntityNotFoundException {
        if (schoolYearDto == null || schoolYearDto.getId() == null) {
            throw new InvalidEntityException("School year cannot be null or have a null ID");
        }

        SchoolYear existingSchoolYear = schoolYearRepository.findById(schoolYearDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("School year not found with id " + schoolYearDto.getId()));

        existingSchoolYear.setWording(schoolYearDto.getWording());
        existingSchoolYear.setStartDate(schoolYearDto.getStartDate());
        existingSchoolYear.setEndDate(schoolYearDto.getEndDate());

        SchoolYear updatedSchoolYear = schoolYearRepository.save(existingSchoolYear);
        return SchoolYearDto.fromEntity(updatedSchoolYear);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, InvalidOperationException {
        Optional<SchoolYear> schoolYearOptional = schoolYearRepository.findById(id);
        if (schoolYearOptional.isEmpty()) {
            throw new EntityNotFoundException("School year with id " + id + " not found");
        }
        SchoolYear schoolYear = schoolYearOptional.get();
        Date now = new Date();
        if (now.before(schoolYear.getStartDate())) {
            throw new InvalidOperationException("Cannot delete a school year that hasn't started yet");
        }
        if (now.after(schoolYear.getEndDate())) {
            throw new InvalidOperationException("Cannot delete a school year that has already ended");
        }
        schoolYearRepository.delete(schoolYear);
    }
}
