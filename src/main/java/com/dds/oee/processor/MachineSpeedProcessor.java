package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.MachineSpeed;
import com.dds.oee.entity.QMachineSpeed;
import com.dds.oee.payload.MachineSpeedModel;
import com.dds.oee.service.MachineSpeedService;
import com.dds.oee.transformer.MachineSplitMap;
import org.springframework.stereotype.Component;

@Component
public class MachineSpeedProcessor extends CommonProcessor<MachineSpeedService, QMachineSpeed, MachineSplitMap> {
    public MachineSpeedProcessor(MachineSpeedService service, MachineSplitMap transformer) {
        super(service, QMachineSpeed.machineSpeed, transformer);
    }

    public MachineSpeedModel getSpeed() {
        MachineSpeed speed = service.getTopByIdDESC();
        return transformer.mapMachineSpeed(speed);
    }
}
