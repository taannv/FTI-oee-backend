package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong03.CanOng03SplitStatus;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong03.CanOng03StatusRepository;
import org.springframework.stereotype.Service;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2031
 */
@Service
public class CanOng03StatusService extends CommonService<CanOng03SplitStatus, Long, CanOng03StatusRepository> {


    protected CanOng03StatusService(CanOng03StatusRepository repo) {
        super(repo);
    }

    public CanOng03SplitStatus findTopByOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
