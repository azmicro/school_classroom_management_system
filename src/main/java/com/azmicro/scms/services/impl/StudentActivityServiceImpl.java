package com.azmicro.scms.services.impl;
import com.azmicro.scms.dto.ActivityDto;
import com.azmicro.scms.dto.StudentActivityDto;
import com.azmicro.scms.dto.StudentDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.InvalidEntityException;
import com.azmicro.scms.exception.InvalidOperationException;
import com.azmicro.scms.model.StudentActivity;
import com.azmicro.scms.repository.ActivityRepository;
import com.azmicro.scms.repository.StudentActivityRepository;
import com.azmicro.scms.repository.StudentRepository;
import com.azmicro.scms.services.StudentActivityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class StudentActivityServiceImpl implements StudentActivityService{
    private final StudentActivityRepository studentActivityRepository;
    private final StudentRepository studentRepository;
    private final ActivityRepository activityRepository;

    @Override
    public StudentActivityDto save(StudentActivityDto studentActivityDto) throws InvalidEntityException, EntityNotFoundException {
        if (studentActivityDto == null) {
            throw new InvalidEntityException("StudentActivity cannot be null");
        }

        studentRepository.findById(studentActivityDto.getStudentDto().getId())
                .orElseThrow(() -> new EntityNotFoundException("Student with id " + studentActivityDto.getStudentDto().getId() + " not found"));

        activityRepository.findById(studentActivityDto.getActivityDto().getId())
                .orElseThrow(() -> new EntityNotFoundException("Activity with id " + studentActivityDto.getActivityDto().getId() + " not found"));

        StudentActivity studentActivity = studentActivityRepository.save(StudentActivityDto.toEntity(studentActivityDto));
        return StudentActivityDto.fromEntity(studentActivity);
    }

    @Override
    public StudentActivityDto update(StudentActivityDto studentActivityDto) throws InvalidEntityException, EntityNotFoundException {
        if (studentActivityDto == null || studentActivityDto.getId() == null) {
            throw new InvalidEntityException("Student activity cannot be null or have a null ID");
        }

        // Check if the student activity exists
        StudentActivity existingStudentActivity = studentActivityRepository.findById(studentActivityDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Student activity with ID " + studentActivityDto.getId() + " not found"));

        // Update the existing student activity with the new data
        existingStudentActivity.setDateOfActivity(studentActivityDto.getDateOfActivity());
        existingStudentActivity.setMark(studentActivityDto.getMark());
        existingStudentActivity.setStudent(StudentDto.toEntity(studentActivityDto.getStudentDto()));
        existingStudentActivity.setActivity(ActivityDto.toEntity(studentActivityDto.getActivityDto()));

        // Save the updated student activity
        StudentActivity savedStudentActivity = studentActivityRepository.save(existingStudentActivity);

        // Return the DTO of the saved student activity
        return StudentActivityDto.fromEntity(savedStudentActivity);
    }

    @Override
    public void delete(Long id) throws EntityNotFoundException, InvalidOperationException {
        Optional<StudentActivity> optionalStudentActivity = studentActivityRepository.findById(id);
        if (optionalStudentActivity.isEmpty()) {
            throw new EntityNotFoundException("Student activity with id " + id + " not found");
        }
        StudentActivity studentActivity = optionalStudentActivity.get();

        studentActivityRepository.deleteById(id);
    }

    @Override
    public StudentActivityDto findById(Long id) throws EntityNotFoundException {
        StudentActivity studentActivity = studentActivityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No student activity found with id " + id));
        return StudentActivityDto.fromEntity(studentActivity);
    }

    @Override
    public List<StudentActivityDto> findByStudentId(Long studentId) {
        List<StudentActivity> studentActivities = studentActivityRepository.findByStudentId(studentId);
        return studentActivities.stream()
                .map(StudentActivityDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentActivityDto> findByActivityId(Long activityId) {
        List<StudentActivity> studentActivities = studentActivityRepository.findByActivityId(activityId);
        return studentActivities.stream()
                .map(StudentActivityDto::fromEntity)
                .collect(Collectors.toList());
    }

    public List<StudentActivityDto> findByStudentIdAndActivityId(Long studentId, Long activityId) throws EntityNotFoundException {
        List<StudentActivity> studentActivities = studentActivityRepository.findByStudentIdAndActivityId(studentId, activityId);
        if (studentActivities.isEmpty()) {
            throw new EntityNotFoundException("No student activities found for student id " + studentId + " and activity id " + activityId);
        }
        return studentActivities.stream()
                .map(StudentActivityDto::fromEntity)
                .collect(Collectors.toList());
    }
}
