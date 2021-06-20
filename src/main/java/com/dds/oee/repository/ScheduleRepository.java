package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.ScheduleCalendar;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleRepository extends CommonRepository<ScheduleCalendar,Long> {
    Optional<ScheduleCalendar> findById(Long id);
}
