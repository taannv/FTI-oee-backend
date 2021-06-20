package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong03.CanOng03SplitError;
import com.dds.oee.repositorycanong03.CanOng03SplitErrorRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Service
public class CanOng03SplitErrorService extends CommonService<CanOng03SplitError, Long, CanOng03SplitErrorRepository> {

    protected CanOng03SplitErrorService(CanOng03SplitErrorRepository repo) {
        super(repo);
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
