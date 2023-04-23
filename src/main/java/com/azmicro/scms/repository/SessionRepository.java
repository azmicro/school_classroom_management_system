package com.azmicro.scms.repository;

import com.azmicro.scms.model.Session;
import com.azmicro.scms.model.Grade;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByGrade(Grade grade);

}
