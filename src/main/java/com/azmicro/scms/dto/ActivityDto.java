package com.azmicro.scms.dto;

import com.azmicro.scms.model.Activity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ActivityDto {

    private Long id;
    private String wording;
    private SemesterDto semesterDto;

    public static ActivityDto fromEntity(Activity activity){
        if(activity== null){return null;}
        return ActivityDto.builder()
                .id(activity.getId())
                .wording(activity.getWording())
                .semesterDto(SemesterDto.fromEntity(activity.getSemester()))
                .build();
    }
    public static Activity toEntity(ActivityDto activityDto){
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setWording(activityDto.getWording());
        activity.setSemester(SemesterDto.toEntity(activityDto.getSemesterDto()));
        return activity;
    }
}
