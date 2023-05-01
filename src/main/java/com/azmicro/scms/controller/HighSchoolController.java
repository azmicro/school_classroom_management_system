package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.HighSchoolApi;
import com.azmicro.scms.controller.api.SchoolYearApi;
import com.azmicro.scms.dto.HighSchoolDto;
import com.azmicro.scms.dto.SchoolYearDto;
import com.azmicro.scms.services.HighSchoolService;
import com.azmicro.scms.services.SchoolYearService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class HighSchoolController implements HighSchoolApi {
    private HighSchoolService highSchoolService;
    @Override
    public List<HighSchoolDto> findAll() {
        return highSchoolService.findAll();
    }

    @Override
    public HighSchoolDto findById(Long id) {
        return highSchoolService.findById(id);
    }

    @Override
    public HighSchoolDto save(HighSchoolDto highSchoolDto) {
        return highSchoolService.save(highSchoolDto);
    }

    @Override
    public void deleteById(Long id) {
        highSchoolService.deleteById(id);
    }

    @Override
    public List<HighSchoolDto> findByNameAr(String nameAr) {
        return highSchoolService.findByNameAr(nameAr);
    }

    @Override
    public List<HighSchoolDto> findByNameFr(String nameFr) {
        return highSchoolService.findByNameFr(nameFr);
    }

    @Override
    public List<HighSchoolDto> findByAdress(String address) {
        return highSchoolService.findByAdress(address);
    }

    @Override
    public List<HighSchoolDto> findByGradeId(Long gradeId) {
        return null;
    }

    @RestController
    @AllArgsConstructor
    @CrossOrigin("*")
    public static class SchollYearController implements SchoolYearApi {
        private SchoolYearService schoolYearService;
        @Override
        public SchoolYearDto findById(Long id) {
            return schoolYearService.findById(id);
        }

        @Override
        public List<SchoolYearDto> findAll() {
            return schoolYearService.findAll();
        }

        @Override
        public SchoolYearDto findByWording(String wording) {
            return schoolYearService.findByWording(wording);
        }

        @Override
        public List<SchoolYearDto> findAllByStartDateGreaterThanEqual(Date startDate) {
            return schoolYearService.findAllByStartDateGreaterThanEqual(startDate);
        }

        @Override
        public List<SchoolYearDto> findAllByEndDateLessThanEqual(Date endDate) {
            return schoolYearService.findAllByEndDateLessThanEqual(endDate);
        }

        @Override
        public SchoolYearDto save(SchoolYearDto schoolYearDto) {
            return schoolYearService.save(schoolYearDto);
        }

        @Override
        public void delete(Long id) {
            schoolYearService.delete(id);
        }
    }
}
