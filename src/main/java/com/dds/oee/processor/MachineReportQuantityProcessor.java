package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.MachineQuantity;
import com.dds.oee.entity.QMachineQuantity;
import com.dds.oee.payload.MachineSplitModel;
import com.dds.oee.service.MachineReportQuantityService;
import com.dds.oee.transformer.MachineSplitMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author: Phan Thi Hong
 *
 * May 26, 2020
 *
 */
 @Component
public class MachineReportQuantityProcessor extends CommonProcessor<MachineReportQuantityService, QMachineQuantity, MachineSplitMap> {

    public MachineReportQuantityProcessor(MachineReportQuantityService service, MachineSplitMap transformer) {
        super(service, QMachineQuantity.machineQuantity, transformer);
    }

    public List<MachineSplitModel> getProductionByMonth() {
        List<MachineQuantity> machineQuantities = service.getProductionByMonth();
        List<MachineSplitModel > ls = new ArrayList<>();
        for(MachineQuantity pro : machineQuantities){
            MachineSplitModel machineSplitModel = transformer.toModel(pro);
            ls.add(machineSplitModel);
        }

        return ls;
    }
}
