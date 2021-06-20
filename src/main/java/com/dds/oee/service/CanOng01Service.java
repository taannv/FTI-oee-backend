package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01Split;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong01.CanOng01Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 15, 2021
 *
 */
@Service
public class CanOng01Service extends CommonService<CanOng01Split, Long, CanOng01Repository> {

    protected CanOng01Service(CanOng01Repository repo) {
        super(repo);
    }

    public CanOng01Split findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
