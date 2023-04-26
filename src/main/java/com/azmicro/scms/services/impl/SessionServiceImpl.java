package com.azmicro.scms.services.impl;

import com.azmicro.scms.dto.SessionDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.Session;
import com.azmicro.scms.repository.SessionRepository;
import com.azmicro.scms.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public SessionDto findById(Long id) throws EntityNotFoundException {
        Session session = sessionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Session with id " + id + " not found"));
        return SessionDto.fromEntity(session);
    }

    @Override
    public List<SessionDto> findAll() {
        return sessionRepository.findAll().stream()
                .map(SessionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SessionDto save(SessionDto sessionDto) throws InvalidEntityException {
        if (sessionDto.getId() != null) {
            throw new InvalidEntityException("The ID must be null for a new session");
        }
        Session session = SessionDto.toEntity(sessionDto);
        Session savedSession = sessionRepository.save(session);
        return SessionDto.fromEntity(savedSession);
    }

    @Override
    public SessionDto update(SessionDto sessionDto) throws InvalidEntityException, EntityNotFoundException {
        if (sessionDto.getId() == null) {
            throw new InvalidEntityException("The ID must not be null for an existing session");
        }
        Session session = SessionDto.toEntity(sessionDto);
        if (!sessionRepository.existsById(sessionDto.getId())) {
            throw new EntityNotFoundException("Session with id " + sessionDto.getId() + " not found");
        }
        Session updatedSession = sessionRepository.save(session);
        return SessionDto.fromEntity(updatedSession);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, InvalidOperationException {
        SessionDto sessionDto = findById(id);
        if (sessionDto == null) {
            throw new EntityNotFoundException("Session with id " + id + " not found");
        }
        sessionRepository.deleteById(id);
    }

    @Override
    public List<SessionDto> findByGradeId(Long gradeId) {
        return sessionRepository.findByGradeId(gradeId).stream()
                .map(SessionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
