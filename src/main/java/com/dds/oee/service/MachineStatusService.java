package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.MachineSplitStatus;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.MachineStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class MachineStatusService extends CommonService<MachineSplitStatus, Long, MachineStatusRepository> {
    public MachineStatusService(MachineStatusRepository repo) {
        super(repo);
    }

    public MachineSplitStatus findTopByOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
