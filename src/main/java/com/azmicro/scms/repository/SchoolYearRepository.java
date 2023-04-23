package com.azmicro.scms.repository;

import com.azmicro.scms.model.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {
    SchoolYear findByWording(String wording);
    List<SchoolYear> findAllByOrderByStartDateAsc();

    List<SchoolYear> findAllByOrderByEndDateAsc();

    List<SchoolYear> findAllByStartDateGreaterThanEqualOrderByStartDateAsc(Date startDate);

    List<SchoolYear> findAllByEndDateLessThanEqualOrderByEndDateAsc(Date endDate);

}
