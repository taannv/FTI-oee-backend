package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.MachineSplitError;
import com.dds.oee.repository.MachineSplitErrorRepository;
import org.springframework.stereotype.Service;

@Service
public class MachineSplitErrorService extends CommonService<MachineSplitError,Long, MachineSplitErrorRepository> {

    public MachineSplitErrorService(MachineSplitErrorRepository repo){
        super(repo);
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
