package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.HighSchoolDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.model.HighSchool;
import com.azmicro.scms.repository.HighSchoolRepository;
import com.azmicro.scms.services.HighSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
@Transactional
public class HighScoolServiceImpl implements HighSchoolService{
    private HighSchoolRepository highSchoolRepository;


    @Override
    public List<HighSchoolDto> findAll() {
        List<HighSchool> highSchools = highSchoolRepository.findAll();
        return highSchools.stream()
                .map(HighSchoolDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public HighSchoolDto findById(Long id) throws EntityNotFoundException {
        HighSchool highSchool = highSchoolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HighSchool with id " + id + " not found"));
        return HighSchoolDto.fromEntity(highSchool);
    }

    @Override
    public HighSchoolDto save(HighSchoolDto highSchoolDto) throws InvalidEntityException {
        if(highSchoolDto == null){
            throw new InvalidEntityException("HighSchool cannot be null");
        }
        HighSchool highSchool = HighSchoolDto.toEntity(highSchoolDto);
        highSchool = highSchoolRepository.save(highSchool);
        return HighSchoolDto.fromEntity(highSchool);
    }

    @Override
    public void deleteById(Long id) throws EntityNotFoundException {
        if(id == null){
            throw new InvalidEntityException("Id cannot be null");
        }
        if(!highSchoolRepository.existsById(id)){
            throw new EntityNotFoundException("HighSchool with id " + id + " not found");
        }
        highSchoolRepository.deleteById(id);
    }

    @Override
    public List<HighSchoolDto> findByNameAr(String nameAr) {
        List<HighSchool> highSchools = highSchoolRepository.findByNameAr(nameAr);
        return highSchools.stream()
                .map(HighSchoolDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HighSchoolDto> findByNameFr(String nameFr) {
        List<HighSchool> highSchools = highSchoolRepository.findByNameFr(nameFr);
        return highSchools.stream()
                .map(HighSchoolDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HighSchoolDto> findByAdress(String adress) {
        List<HighSchool> highSchools = highSchoolRepository.findByAdress(adress);
        if(highSchools.isEmpty()) {
            throw new EntityNotFoundException("No high school found with this address: " + adress);
        }
        return highSchools.stream()
                .map(HighSchoolDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<HighSchoolDto> findByGradeId(Long gradeId) {
        List<HighSchool> highSchools = highSchoolRepository.findByGrades_Id(gradeId);
        if(highSchools.isEmpty()) {
            throw new EntityNotFoundException("No high school found with grade id: " + gradeId);
        }
        return highSchools.stream()
                .map(HighSchoolDto::fromEntity)
                .collect(Collectors.toList());
    }

}
