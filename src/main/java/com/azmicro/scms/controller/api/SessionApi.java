package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.SessionDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface SessionApi {
    @GetMapping(value= APP_ROOT + "/sessions/{idSession}", produces= MediaType.APPLICATION_JSON_VALUE)
    SessionDto findById(@PathVariable("idSession") Long id);
    @GetMapping(value= APP_ROOT + "/sessions/all", produces= MediaType.APPLICATION_JSON_VALUE)
    List<SessionDto> findAll();
    @PostMapping(value = APP_ROOT+"/sessions/create",consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
    SessionDto save(@RequestBody SessionDto sessionDto);
    @DeleteMapping(value = APP_ROOT+"/sessions/delete/{idSession}")
    void delete(@PathVariable("idSession") Long id);
    @GetMapping(value = APP_ROOT + "/sessions/grade/{idGrade}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<SessionDto> findByGradeId(@PathVariable("idGrade") Long gradeId);
}
