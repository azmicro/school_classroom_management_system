package com.azmicro.scms.controller;

import com.azmicro.scms.controller.api.ActivityApi;
import com.azmicro.scms.dto.ActivityDto;
import com.azmicro.scms.services.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class ActivityController implements ActivityApi {

    private ActivityService activityService;
    @Override
    public ActivityDto save(ActivityDto activityDto) {
        return activityService.save(activityDto);
    }

    @Override
    public ActivityDto findById(Long id) {
        return activityService.findById(id);
    }

    @Override
    public List<ActivityDto> findAll() {
        return activityService.findAll();
    }

    @Override
    public void deleteById(Long id) {
        activityService.deleteById(id);
    }

    @Override
    public List<ActivityDto> findBySemesterId(Long semesterId) {
        return activityService.findBySemesterId(semesterId);
    }

    @Override
    public List<ActivityDto> findByWordingContainingIgnoreCase(String keyword) {
        return activityService.findByWordingContainingIgnoreCase(keyword);
    }

    @Override
    public ActivityDto findByWording(String wording) {
        return activityService.findByWording(wording);
    }
}
