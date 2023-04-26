package com.azmicro.scms.services;

import com.azmicro.scms.dto.SessionDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.List;
public interface SessionService {
    SessionDto findById(Long id) throws EntityNotFoundException;
    List<SessionDto> findAll();
    SessionDto save(SessionDto sessionDto) throws InvalidEntityException;
    SessionDto update(SessionDto sessionDto) throws InvalidEntityException, EntityNotFoundException;
    void delete(Long id) throws EntityNotFoundException, InvalidOperationException;
    List<SessionDto> findByGradeId(Long gradeId);
}
