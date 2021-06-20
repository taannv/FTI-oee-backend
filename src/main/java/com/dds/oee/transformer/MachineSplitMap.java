package com.dds.oee.transformer;

import com.dds.oee.entity.*;
import com.dds.oee.payload.MachineSpeedModel;
import com.dds.oee.payload.MachineSplitErrorModel;
import com.dds.oee.payload.MachineSplitModel;
import com.dds.oee.payload.MachineStatusModel;
import com.dds.oee.payload.ProductChartModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MachineSplitMap {

    public List<MachineSplitModel> toModels(List<MachineSplit> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public MachineSplitModel toModel(MachineSplit entity) {
        MachineSplitModel machineSplitModel = new MachineSplitModel();
        machineSplitModel.setId(entity.getId());
        machineSplitModel.setDate(entity.getDate1());
        machineSplitModel.setHour(entity.getGio());
        machineSplitModel.setDay(entity.getNgay());
        machineSplitModel.setPlan(entity.getKlkh());
        machineSplitModel.setProduce(entity.getKlsx());
        machineSplitModel.setTimePlan(entity.getTgkh());
        machineSplitModel.setTimeProduce(entity.getTgsx());
        machineSplitModel.setOee(entity.getOee());
        machineSplitModel.setTimeOn(entity.getTgon());
        machineSplitModel.setTimeOff(entity.getTgoff());
        machineSplitModel.setOeeA(entity.getOeeA());
        machineSplitModel.setOeeQ(entity.getOeeQ());
        machineSplitModel.setOeeP(entity.getOeeP());

        return machineSplitModel;
    }

    public MachineSplitModel toModel(ProductionOrder entity) {
        MachineSplitModel machineSplitModel = new MachineSplitModel();
        machineSplitModel.setId(entity.getId());
        machineSplitModel.setDate(entity.getDate1());
        machineSplitModel.setProductionOrder(entity.getLenh_sx());
        machineSplitModel.setHour(entity.getGio());
        machineSplitModel.setDay(entity.getNgay());
        machineSplitModel.setWidth(entity.getChieu_rong());
        machineSplitModel.setHeight(entity.getDo_day());
        machineSplitModel.setMass(entity.getKhoi_luong());
        machineSplitModel.setWidth1(entity.getCrong1());
        machineSplitModel.setAmount1(entity.getSluong1());
        machineSplitModel.setWidth2(entity.getCrong2());
        machineSplitModel.setAmount2(entity.getSluong2());
        machineSplitModel.setWidth3(entity.getCrong3());
        machineSplitModel.setAmount3(entity.getSluong3());
        machineSplitModel.setWidth4(entity.getCrong4());
        machineSplitModel.setAmount4(entity.getSluong4());
        machineSplitModel.setWidth5(entity.getCrong5());
        machineSplitModel.setAmount5(entity.getSluong5());
        machineSplitModel.setBienT(entity.getBient());
        machineSplitModel.setBienN(entity.getBienn());
        return machineSplitModel;
    }

    public MachineSplitModel toModel(MachineQuantity entity) {
        MachineSplitModel machineSplitModel = new MachineSplitModel();
        machineSplitModel.setDay(entity.getNgay());
        machineSplitModel.setMass(entity.getKhoi_luong());
        return machineSplitModel;
    }
    public ProductChartModel toModelProductionByMonth(ProductionChart entity) {
        ProductChartModel productChartModel = new ProductChartModel();
        productChartModel.setDay(entity.getNgay());
        productChartModel.setMass(entity.getKhoi_luong());

        return productChartModel;
    }

    public MachineSplitErrorModel mapMachineError(MachineSplitError entity) {
        MachineSplitErrorModel model = new MachineSplitErrorModel();
        model.setHourStart(entity.getGio_start());
        model.setDayStart(entity.getNgay_start());
        model.setHourEnd(entity.getGio_end());
        model.setDayEnd(entity.getNgay_end());
        model.setNote(entity.getNguyen_nhan());
        return model;
    }

    public MachineStatusModel mapMachineStatus(MachineSplitStatus entity) {
        MachineStatusModel machineStatus = new MachineStatusModel();
        machineStatus.setStatus(entity.getTrang_thai_ON().trim().toUpperCase().equals("TRUE"));

        return machineStatus;
    }

    public MachineSpeedModel mapMachineSpeed(MachineSpeed entity) {
        MachineSpeedModel model = new MachineSpeedModel();
        model.setSpeed(entity.getToc_do());
        return model;
    }

}
