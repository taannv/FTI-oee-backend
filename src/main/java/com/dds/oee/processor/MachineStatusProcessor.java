package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QMachineSplitStatus;
import com.dds.oee.payload.MachineStatusModel;
import com.dds.oee.service.MachineStatusService;
import com.dds.oee.transformer.MachineSplitMap;
import org.springframework.stereotype.Component;

@Component
public class MachineStatusProcessor extends CommonProcessor<MachineStatusService, QMachineSplitStatus, MachineSplitMap> {

    public MachineStatusProcessor(MachineStatusService service, MachineSplitMap transformer) {
        super(service, QMachineSplitStatus.machineSplitStatus, transformer);
    }

    public MachineStatusModel getMachineStatus() {
        return transformer.mapMachineStatus(service.findTopByOrderByIdDesc());
    }
}
