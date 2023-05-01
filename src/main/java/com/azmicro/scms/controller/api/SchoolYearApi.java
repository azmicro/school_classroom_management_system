package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.SchoolYearDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface SchoolYearApi {
    @GetMapping(value = APP_ROOT+"/schoolyears/{idSchoolYear}", produces = MediaType.APPLICATION_JSON_VALUE)
    SchoolYearDto findById(@PathVariable("idSchoolYear") Long id);

    @GetMapping(value = APP_ROOT+"/schoolyears/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SchoolYearDto> findAll();
    @GetMapping(value = APP_ROOT+"/schoolyears/{wording}", produces = MediaType.APPLICATION_JSON_VALUE)
    SchoolYearDto findByWording(@PathVariable("wording") String wording);

    @GetMapping(value = APP_ROOT+"/schoolyears/{startDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SchoolYearDto> findAllByStartDateGreaterThanEqual(@PathVariable("startDate") Date startDate);

    @GetMapping(value = APP_ROOT+"/schoolyear/{endDate}", produces =MediaType.APPLICATION_JSON_VALUE)
    List<SchoolYearDto> findAllByEndDateLessThanEqual(@PathVariable("endDate") Date endDate);

    @PostMapping(value = APP_ROOT+"/schoolyears/create",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    SchoolYearDto save(@RequestBody SchoolYearDto schoolYearDto);
    @DeleteMapping(value = APP_ROOT+"/schoolyears/delete/{idSchoolYear}")
    void delete(@PathVariable("idSchoolYear") Long id);
}
