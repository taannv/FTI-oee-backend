package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01SplitError;
import com.dds.oee.repositorycanong01.CanOng01SplitErrorRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 26, 2021
 *
 */
@Service
public class CanOng01SplitErrorService extends CommonService<CanOng01SplitError, Long, CanOng01SplitErrorRepository> {

    protected CanOng01SplitErrorService(CanOng01SplitErrorRepository repo) {
        super(repo);
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
