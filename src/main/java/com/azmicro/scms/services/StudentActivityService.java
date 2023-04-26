package com.azmicro.scms.services;
import com.azmicro.scms.dto.StudentActivityDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;

import java.util.List;
public interface StudentActivityService {
    StudentActivityDto save(StudentActivityDto studentActivityDto) throws InvalidEntityException, EntityNotFoundException;

    StudentActivityDto update(StudentActivityDto studentActivityDto) throws InvalidEntityException, EntityNotFoundException;

    void delete(Long id) throws EntityNotFoundException, InvalidOperationException;
    StudentActivityDto findById(Long id) throws EntityNotFoundException;

    List<StudentActivityDto> findByStudentId(Long studentId);

    List<StudentActivityDto> findByActivityId(Long activityId);

    List<StudentActivityDto> findByStudentIdAndActivityId(Long studentId, Long activityId) throws EntityNotFoundException;
}
