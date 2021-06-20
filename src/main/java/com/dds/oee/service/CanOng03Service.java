package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong03.CanOng03Split;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong03.CanOng03Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Service
public class CanOng03Service extends CommonService<CanOng03Split, Long, CanOng03Repository> {


    protected CanOng03Service(CanOng03Repository repo) {
        super(repo);
    }

    public CanOng03Split findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
