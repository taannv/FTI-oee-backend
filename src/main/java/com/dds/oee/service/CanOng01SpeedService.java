package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01Speed;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong01.CanOng01Repository;
import com.dds.oee.repositorycanong01.CanOng01SpeedRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2021
 */
@Service
public class CanOng01SpeedService extends CommonService<CanOng01Speed, Long, CanOng01SpeedRepository> {


    protected CanOng01SpeedService(CanOng01SpeedRepository repo) {
        super(repo);
    }

    public CanOng01Speed getTopByIdDESC() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
