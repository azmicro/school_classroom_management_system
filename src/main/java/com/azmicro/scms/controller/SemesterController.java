package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.SemesterApi;
import com.azmicro.scms.dto.SemesterDto;
import com.azmicro.scms.services.SemesterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class SemesterController implements SemesterApi {
    private SemesterService semesterService;

    @Override
    public SemesterDto findById(Long id) {
        return semesterService.findById(id);
    }

    @Override
    public List<SemesterDto> findAll() {
        return semesterService.findAll();
    }

    @Override
    public SemesterDto save(SemesterDto semesterDto) {
        return semesterService.save(semesterDto);
    }

    @Override
    public void delete(Long id) {
        semesterService.delete(id);
    }

    @Override
    public List<SemesterDto> findBySchoolYearId(Long id) {
        return semesterService.findBySchoolYearId(id);
    }
}
