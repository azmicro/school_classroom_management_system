package com.azmicro.scms.services;

import com.azmicro.scms.dto.ActivityDto;

import java.util.List;

public interface ActivityService {

    ActivityDto save(ActivityDto activityDto);

    ActivityDto findById(Long id);

    List<ActivityDto> findAll();

    void deleteById(Long id);

    List<ActivityDto> findBySemesterId(Long semesterId);

    List<ActivityDto> findByWordingContainingIgnoreCase(String keyword);

    ActivityDto findByWording(String wording);
}
