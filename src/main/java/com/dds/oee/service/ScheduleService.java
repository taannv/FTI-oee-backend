package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.ScheduleCalendar;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService extends CommonService<ScheduleCalendar, Long, ScheduleRepository> {

    public ScheduleService(ScheduleRepository repo) {
        super(repo);
    }

    public List<ScheduleCalendar> findAllSchedule() {
        return repo.findAll();
    }

    public ScheduleCalendar findById(Long id) {
        return repo.findById(id)
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) id)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
