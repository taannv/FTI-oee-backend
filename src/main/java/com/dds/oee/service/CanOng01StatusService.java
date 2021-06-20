package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01SplitStatus;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong01.CanOng01StatusRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Service
public class CanOng01StatusService extends CommonService<CanOng01SplitStatus, Long, CanOng01StatusRepository> {


    protected CanOng01StatusService(CanOng01StatusRepository repo) {
        super(repo);
    }

    public CanOng01SplitStatus findTopByOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
