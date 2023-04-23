package com.azmicro.scms.repository;

import com.azmicro.scms.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findBySemesterId(Long semesterId);

    List<Activity> findByWordingContainingIgnoreCase(String keyword);

    Activity findByWording(String wording);
}