package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01SplitStatus;
import com.dds.oee.entitycanong02.CanOng02SplitStatus;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong01.CanOng01StatusRepository;
import com.dds.oee.repositorycanong02.CanOng02StatusRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Service
public class CanOng02StatusService extends CommonService<CanOng02SplitStatus, Long, CanOng02StatusRepository> {


    protected CanOng02StatusService(CanOng02StatusRepository repo) {
        super(repo);
    }

    public CanOng02SplitStatus findTopByOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
