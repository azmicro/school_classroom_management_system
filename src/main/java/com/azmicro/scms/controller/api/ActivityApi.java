package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.ActivityDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface ActivityApi {

    @PostMapping(value = APP_ROOT+"/activities/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
    ActivityDto save(@RequestBody ActivityDto activityDto);

    @GetMapping(value =APP_ROOT+"/activities/{idActivity}", produces = MediaType.APPLICATION_JSON_VALUE)
    ActivityDto findById(@PathVariable("idActivity") Long id);

    @GetMapping(value = APP_ROOT+"/activities/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ActivityDto> findAll();

    @DeleteMapping(value = APP_ROOT+"/activities/delete/{idActivity}")
    void deleteById(@PathVariable("idActivity") Long id);

    @GetMapping(value = APP_ROOT+"/activities/{idSemester}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ActivityDto> findBySemesterId(@PathVariable("idSemester") Long semesterId);

    @GetMapping(value = APP_ROOT+"/activities/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<ActivityDto> findByWordingContainingIgnoreCase(@PathVariable("keyword") String keyword);

    @GetMapping(value = APP_ROOT+"/activities/{wording}", produces = MediaType.APPLICATION_JSON_VALUE)
    ActivityDto findByWording(@PathVariable("wording") String wording);
}
