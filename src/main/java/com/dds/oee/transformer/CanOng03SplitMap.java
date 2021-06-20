package com.dds.oee.transformer;

import com.dds.oee.entitycanong03.*;
import com.dds.oee.payload.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2031
 *
 */
@Component
public class CanOng03SplitMap {
    public List<CanOng03Model> toModels(List<CanOng03Split> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public CanOng03Model toModel(CanOng03Split entity) {
        CanOng03Model canOng03Model = new CanOng03Model();
        canOng03Model.setId(entity.getId());
        canOng03Model.setDate1(entity.getDate1());
        canOng03Model.setGio(entity.getGio());
        canOng03Model.setNgay(entity.getNgay());
        canOng03Model.setTgkh(entity.getTgkh());
        canOng03Model.setTgsx(entity.getTgsx());
        canOng03Model.setOee(entity.getOee());
        canOng03Model.setSlkh(entity.getSlkh());
        canOng03Model.setSlsx(entity.getSlsx());
        canOng03Model.setTimeOn(entity.getTgon());
        canOng03Model.setTimeOff(entity.getTgoff());
        canOng03Model.setChinhpham(entity.getChinhpham());
        canOng03Model.setThupham(entity.getThupham());
        canOng03Model.setPhepham(entity.getPhepham());
        canOng03Model.setSlkhtc(entity.getSlkhtc());
        canOng03Model.setKlkhtc(entity.getKlkhtc());
        canOng03Model.setSltttc(entity.getSltttc());
        canOng03Model.setKltttc(entity.getKltttc());
        canOng03Model.setKlkh8t(entity.getKlkh8t());
        canOng03Model.setKltt8t(entity.getKltt8t());
        canOng03Model.setT(entity.getT());
        canOng03Model.setTc(entity.getTc());
        canOng03Model.setOeeA(entity.getOeeA());
        canOng03Model.setOeeP(entity.getOeeP());
        canOng03Model.setOeeQ(entity.getOeeQ());

        return canOng03Model;
    }

    public CanOng03ProductionModel toModel(CanOng03ProductionOrder entity) {
        CanOng03ProductionModel canOng03ProductionOrder = new CanOng03ProductionModel();
        canOng03ProductionOrder.setId(entity.getId());
        canOng03ProductionOrder.setDate1(entity.getDate1());
        canOng03ProductionOrder.setLenh_sx(entity.getLenh_sx());
        canOng03ProductionOrder.setDay_in(entity.getDay_in());
        canOng03ProductionOrder.setGio(entity.getGio());
        canOng03ProductionOrder.setRong_in(entity.getRong_in());
        canOng03ProductionOrder.setCao_out(entity.getCao_out());
        canOng03ProductionOrder.setDai_out(entity.getDai_out());
        canOng03ProductionOrder.setRong_out(entity.getRong_out());
        canOng03ProductionOrder.setSlkh(entity.getSlkh());
        canOng03ProductionOrder.setNgay(entity.getNgay());
        canOng03ProductionOrder.setSltt(entity.getSltt());
        canOng03ProductionOrder.setNhom(entity.getNhom());

        return canOng03ProductionOrder;
    }

    public CanOng03SplitErrorModel mapCanOng03Error(CanOng03SplitError entity) {
        CanOng03SplitErrorModel model = new CanOng03SplitErrorModel();
        model.setHourStart(entity.getGio_start());
        model.setDayStart(entity.getNgay_start());
        model.setHourEnd(entity.getGio_end());
        model.setDayEnd(entity.getNgay_end());
        model.setNote(entity.getNguyen_nhan());
        model.setTgdung(entity.getTgdung());
        return model;
    }

    public CanOng03StatusModel mapCanOng03Status(CanOng03SplitStatus entity) {
        CanOng03StatusModel canOng03Status = new CanOng03StatusModel();
        canOng03Status.setStatus(entity.getTrang_thai_ON().toString().toUpperCase().equals("TRUE"));

//        .trim().toUpperCase()

        return canOng03Status;
    }

    public CanOng03SpeedModel mapCanOng03Speed(CanOng03Speed entity) {
        CanOng03SpeedModel model = new CanOng03SpeedModel();
        model.setSpeed(entity.getToc_do());
        return model;
    }
}
