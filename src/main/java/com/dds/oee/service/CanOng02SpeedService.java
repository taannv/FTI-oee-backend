package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong02.CanOng02Speed;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong02.CanOng02SpeedRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2021
 */
@Service
public class CanOng02SpeedService extends CommonService<CanOng02Speed, Long, CanOng02SpeedRepository> {


    protected CanOng02SpeedService(CanOng02SpeedRepository repo) {
        super(repo);
    }

    public CanOng02Speed getTopByIdDESC() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
