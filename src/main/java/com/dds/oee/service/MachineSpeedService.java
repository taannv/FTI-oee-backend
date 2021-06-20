package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.MachineSpeed;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.MachineSpeedRepository;
import org.springframework.stereotype.Service;

@Service
public class MachineSpeedService extends CommonService<MachineSpeed, Long, MachineSpeedRepository> {

    protected MachineSpeedService(MachineSpeedRepository repo) {
        super(repo);
    }

    public MachineSpeed getTopByIdDESC() {
        return repo.findTopByOrderByIdDesc().orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
