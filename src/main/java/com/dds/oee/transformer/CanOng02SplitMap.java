package com.dds.oee.transformer;

import com.dds.oee.entitycanong02.*;
import com.dds.oee.payload.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Component
public class CanOng02SplitMap {
    public List<CanOng02Model> toModels(List<CanOng02Split> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public CanOng02Model toModel(CanOng02Split entity) {
        CanOng02Model canOng02Model = new CanOng02Model();
        canOng02Model.setId(entity.getId());
        canOng02Model.setDate1(entity.getDate1());
        canOng02Model.setNgay(entity.getNgay());
        canOng02Model.setGio(entity.getGio());
        canOng02Model.setTgkh(entity.getTgkh());
        canOng02Model.setTgsx(entity.getTgsx());
        canOng02Model.setTimeOn(entity.getTgon());
        canOng02Model.setTimeOff(entity.getTgoff());
        canOng02Model.setSlkh(entity.getSlkh());
        canOng02Model.setSlsx(entity.getSlsx());
        canOng02Model.setOee(entity.getOee());
        canOng02Model.setSlkh8t(entity.getSlkh8t());
        canOng02Model.setKlkh8t(entity.getKlkh8t());
        canOng02Model.setSltt8t(entity.getSltt8t());
        canOng02Model.setKltt8t(entity.getKltt8t());
        canOng02Model.setT(entity.getT());
        canOng02Model.setSltttc(entity.getSltttc());
        canOng02Model.setKlkhtc(entity.getKlkhtc());
        canOng02Model.setSltttc(entity.getSltttc());
        canOng02Model.setSltttc(entity.getSltttc());
        canOng02Model.setTc(entity.getTc());
        canOng02Model.setChinhpham(entity.getChinhpham());
        canOng02Model.setThupham(entity.getThupham());
        canOng02Model.setPhepham(entity.getPhepham());
        canOng02Model.setOeeA(entity.getOeeA());
        canOng02Model.setOeeP(entity.getOeeP());
        canOng02Model.setOeeQ(entity.getOeeQ());


        return canOng02Model;
    }

    public CanOng02ProductionModel toModel(CanOng02ProductionOrder entity) {
        CanOng02ProductionModel canOng02ProductionOrder = new CanOng02ProductionModel();
        canOng02ProductionOrder.setId(entity.getId());
        canOng02ProductionOrder.setDate1(entity.getDate1());
        canOng02ProductionOrder.setLenh_sx(entity.getLenh_sx());
        canOng02ProductionOrder.setDay_in(entity.getDay_in());
        canOng02ProductionOrder.setGio(entity.getGio());
        canOng02ProductionOrder.setRong_in(entity.getRong_in());
        canOng02ProductionOrder.setCao_out(entity.getCao_out());
        canOng02ProductionOrder.setDai_out(entity.getDai_out());
        canOng02ProductionOrder.setRong_out(entity.getRong_out());
        canOng02ProductionOrder.setSlkh(entity.getSlkh());
        canOng02ProductionOrder.setNgay(entity.getNgay());
        canOng02ProductionOrder.setSltt(entity.getSltt());
        canOng02ProductionOrder.setNhom(entity.getNhom());

        return canOng02ProductionOrder;
    }

    public CanOng02SplitErrorModel mapCanOng02Error(CanOng02SplitError entity) {
        CanOng02SplitErrorModel model = new CanOng02SplitErrorModel();
        model.setHourStart(entity.getGio_start());
        model.setDayStart(entity.getNgay_start());
        model.setHourEnd(entity.getGio_end());
        model.setDayEnd(entity.getNgay_end());
        model.setNote(entity.getNguyen_nhan());
        model.setTgdung(entity.getTgdung());
        return model;
    }

    public CanOng02StatusModel mapCanOng02Status(CanOng02SplitStatus entity) {
        CanOng02StatusModel canOng02Status = new CanOng02StatusModel();
        canOng02Status.setStatus(entity.getTrang_thai_ON().toString().toUpperCase().equals("TRUE"));

        return canOng02Status;
    }

    public CanOng02SpeedModel mapCanOng02Speed(CanOng02Speed entity) {
        CanOng02SpeedModel model = new CanOng02SpeedModel();
        model.setSpeed(entity.getToc_do());
        return model;
    }
}
