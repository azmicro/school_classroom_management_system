package com.azmicro.scms.repository;

import com.azmicro.scms.model.Activity;
import com.azmicro.scms.model.Student;
import com.azmicro.scms.model.StudentActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentActivityRepository extends JpaRepository<StudentActivity, Long> {

    List<StudentActivity> findByStudentId(Long studentId);

    List<StudentActivity> findByActivityId(Long activityId);

    List<StudentActivity> findByStudentIdAndActivityId(Long studentId, Long activityId);
}
