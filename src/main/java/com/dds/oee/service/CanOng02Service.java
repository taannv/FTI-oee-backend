package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong02.CanOng02Split;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong02.CanOng02Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Service
public class CanOng02Service extends CommonService<CanOng02Split, Long, CanOng02Repository> {


    protected CanOng02Service(CanOng02Repository repo) {
        super(repo);
    }

    public CanOng02Split findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
