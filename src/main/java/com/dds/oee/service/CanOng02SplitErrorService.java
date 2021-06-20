package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01SplitError;
import com.dds.oee.entitycanong02.CanOng02SplitError;
import com.dds.oee.repositorycanong01.CanOng01SplitErrorRepository;
import com.dds.oee.repositorycanong02.CanOng02SplitErrorRepository;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Service
public class CanOng02SplitErrorService extends CommonService<CanOng02SplitError, Long, CanOng02SplitErrorRepository> {

    protected CanOng02SplitErrorService(CanOng02SplitErrorRepository repo) {
        super(repo);
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
