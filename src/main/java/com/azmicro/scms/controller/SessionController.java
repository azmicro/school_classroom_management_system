package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.SessionApi;
import com.azmicro.scms.dto.SessionDto;
import com.azmicro.scms.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class SessionController implements SessionApi {
    private SessionService sessionService;

    @Override
    public SessionDto findById(Long id) {
        return sessionService.findById(id);
    }

    @Override
    public List<SessionDto> findAll() {
        return sessionService.findAll();
    }

    @Override
    public SessionDto save(SessionDto sessionDto) {
        return sessionService.save(sessionDto);
    }

    @Override
    public void delete(Long id) {
        sessionService.delete(id);
    }

    @Override
    public List<SessionDto> findByGradeId(Long gradeId) {
        return sessionService.findByGradeId(gradeId);
    }
}
