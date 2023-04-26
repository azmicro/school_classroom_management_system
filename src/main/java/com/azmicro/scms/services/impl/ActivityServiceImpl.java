package com.azmicro.scms.services.impl;

import com.azmicro.scms.dto.ActivityDto;
import com.azmicro.scms.exception.EntityNotFoundException;
import com.azmicro.scms.exception.ErrorCodes;
import com.azmicro.scms.model.Activity;
import com.azmicro.scms.repository.ActivityRepository;
import com.azmicro.scms.services.ActivityService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    @Override
    public ActivityDto save(ActivityDto activityDto) {
        Activity activity = ActivityDto.toEntity(activityDto);
        activity = activityRepository.save(activity);
        return ActivityDto.fromEntity(activity);
    }

    @Override
    public ActivityDto findById(Long id) {
        if(id == null) {

            log.error("Activity id is null");
            return null;
        }
        Optional<Activity> activity = activityRepository.findById(id);
        return activity.map(ActivityDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Activity was not found", ErrorCodes.ACTIVITY_NOT_FOUND));
    }

    @Override
    public List<ActivityDto> findAll() {
        return activityRepository.findAll().stream().map(ActivityDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<ActivityDto> findBySemesterId(Long semesterId) {
        return activityRepository.findBySemesterId(semesterId).stream().map(ActivityDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<ActivityDto> findByWordingContainingIgnoreCase(String keyword) {
        return activityRepository.findByWordingContainingIgnoreCase(keyword).stream().map(ActivityDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public ActivityDto findByWording(String wording) {
        Activity activity = activityRepository.findByWording(wording);
        if(activity == null){
            throw new EntityNotFoundException("Activity was not found", ErrorCodes.ACTIVITY_NOT_FOUND);
        }
        return ActivityDto.fromEntity(activity);
    }
}
