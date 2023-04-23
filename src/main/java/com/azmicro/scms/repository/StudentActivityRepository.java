package com.azmicro.scms.repository;

import com.azmicro.scms.model.Activity;
import com.azmicro.scms.model.Student;
import com.azmicro.scms.model.StudentActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentActivityRepository extends JpaRepository<StudentActivity, Long> {

    List<StudentActivity> findByStudent(Student student);

    List<StudentActivity> findByActivity(Activity activity);

    List<StudentActivity> findByStudentAndActivity(Student student, Activity activity);
}
