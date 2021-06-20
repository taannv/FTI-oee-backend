package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong03.CanOng03Speed;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong03.CanOng03SpeedRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2031
 */
@Service
public class CanOng03SpeedService extends CommonService<CanOng03Speed, Long, CanOng03SpeedRepository> {


    protected CanOng03SpeedService(CanOng03SpeedRepository repo) {
        super(repo);
    }

    public CanOng03Speed getTopByIdDESC() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
