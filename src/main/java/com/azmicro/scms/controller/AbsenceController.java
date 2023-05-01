package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.AbsenceApi;
import com.azmicro.scms.dto.AbsenceDto;
import com.azmicro.scms.services.AbsenceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class AbsenceController implements AbsenceApi {
    private AbsenceService absenceService;
    @Override
    public AbsenceDto save(AbsenceDto dto) {
        return absenceService.save(dto);
    }

    @Override
    public void delete(Long id) {
        absenceService.deleteById(id);
    }

    @Override
    public AbsenceDto findById(Long id) {
        return absenceService.findById(id);
    }

    @Override
    public List<AbsenceDto> findAll() {
        return absenceService.findAll();
    }

    @Override
    public List<AbsenceDto> findByStudentId(Long studentId) {
        return absenceService.findByStudentId(studentId);
    }

    @Override
    public List<AbsenceDto> findBySemesterId(Long semesterId) {
        return absenceService.findBySemesterId(semesterId);
    }

    @Override
    public List<AbsenceDto> findByStudentAndSemester(Long studentId, Long semesterId) {
        return absenceService.findByStudentAndSemester(studentId,semesterId);
    }
}
