package com.dds.oee.transformer;

import com.dds.oee.entitycanong01.*;
import com.dds.oee.payload.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 15, 2021
 *
 */
@Component
public class CanOng01SplitMap {

    public List<CanOng01Model> toModels(List<CanOng01Split> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public CanOng01Model toModel(CanOng01Split entity) {
        CanOng01Model canOng01Model = new CanOng01Model();
        canOng01Model.setId(entity.getId());
        canOng01Model.setDate1(entity.getDate1());
        canOng01Model.setNgay(entity.getNgay());
        canOng01Model.setGio(entity.getGio());
        canOng01Model.setTgkh(entity.getTgkh());
        canOng01Model.setTgsx(entity.getTgsx());
        canOng01Model.setTimeOn(entity.getTgon());
        canOng01Model.setTimeOff(entity.getTgoff());
        canOng01Model.setSlkh(entity.getSlkh());
        canOng01Model.setSlsx(entity.getSlsx());
        canOng01Model.setOee(entity.getOee());
        canOng01Model.setSlkh8t(entity.getSlkh8t());
        canOng01Model.setKlkh8t(entity.getKlkh8t());
        canOng01Model.setSltt8t(entity.getSltt8t());
        canOng01Model.setKltt8t(entity.getKltt8t());
        canOng01Model.setT(entity.getT());
        canOng01Model.setSltttc(entity.getSltttc());
        canOng01Model.setKlkhtc(entity.getKlkhtc());
        canOng01Model.setSltttc(entity.getSltttc());
        canOng01Model.setSltttc(entity.getSltttc());
        canOng01Model.setTc(entity.getTc());
        canOng01Model.setChinhpham(entity.getChinhpham());
        canOng01Model.setThupham(entity.getThupham());
        canOng01Model.setPhepham(entity.getPhepham());
        canOng01Model.setOeeA(entity.getOeeA());
        canOng01Model.setOeeQ(entity.getOeeQ());
        canOng01Model.setOeeP(entity.getOeeP());

        return canOng01Model;
    }

    public CanOng01ProductionModel toModel(CanOng01ProductionOrder entity) {
        CanOng01ProductionModel canOng01ProductionOrder = new CanOng01ProductionModel();
        canOng01ProductionOrder.setId(entity.getId());
        canOng01ProductionOrder.setDate1(entity.getDate1());
        canOng01ProductionOrder.setNgay(entity.getNgay());
        canOng01ProductionOrder.setGio(entity.getGio());
        canOng01ProductionOrder.setLenh_sx(entity.getLenh_sx());
        canOng01ProductionOrder.setDay_in(entity.getDay_in());
        canOng01ProductionOrder.setRong_in(entity.getRong_in());
        canOng01ProductionOrder.setDai_out(entity.getDai_out());
        canOng01ProductionOrder.setRong_out(entity.getRong_out());
        canOng01ProductionOrder.setCao_out(entity.getCao_out());
        canOng01ProductionOrder.setSlkh(entity.getSlkh());
        canOng01ProductionOrder.setSltt(entity.getSltt());
        canOng01ProductionOrder.setNhom(entity.getNhom());

        return canOng01ProductionOrder;
    }

    public CanOng01SplitErrorModel mapCanOng01Error(CanOng01SplitError entity) {
        CanOng01SplitErrorModel model = new CanOng01SplitErrorModel();
        model.setDate1(entity.getDate1());
        model.setDayStart(entity.getNgay_start());
        model.setHourStart(entity.getGio_start());
        model.setDayEnd(entity.getNgay_end());
        model.setHourEnd(entity.getGio_end());
        model.setNote(entity.getNguyen_nhan());
        model.setTgdung(entity.getTgdung());

        return model;
    }

    public CanOng01StatusModel mapCanOng01Status(CanOng01SplitStatus entity) {
        CanOng01StatusModel canOng01Status = new CanOng01StatusModel();
        canOng01Status.setStatus(entity.getTrang_thai_ON().toString().toUpperCase().equals("TRUE"));

        return canOng01Status;
    }

    public CanOng01SpeedModel mapCanOng01Speed(CanOng01Speed entity) {
        CanOng01SpeedModel model = new CanOng01SpeedModel();
        model.setSpeed(entity.getToc_do());
        return model;
    }

}
