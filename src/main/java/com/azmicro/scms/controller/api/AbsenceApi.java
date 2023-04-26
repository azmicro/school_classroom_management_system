package com.azmicro.scms.controller.api;

import com.azmicro.scms.dto.AbsenceDto;
import com.azmicro.scms.utils.Constants;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;

import static com.azmicro.scms.utils.Constants.APP_ROOT;

public interface AbsenceApi {
    @PostMapping(value = APP_ROOT+"/absence", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    AbsenceDto save(@RequestBody AbsenceDto dto);
}
