package com.dds.oee.transformer;

import com.dds.oee.api.ScheduleApi;
import com.dds.oee.entity.ScheduleCalendar;
import com.dds.oee.payload.ScheduleModel;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ScheduleTransformer {

    public ScheduleCalendar toEntity(ScheduleApi.ScheduleCreateModel model, UserPrincipal currentUser) {
        ScheduleCalendar entity = new ScheduleCalendar();
        entity.setStart(DateUtils.at(model.getStart()));
        entity.setEndAt(DateUtils.at(model.getEnd()));
        entity.setTitle(model.getTitle());
        entity.setContent(model.getContent());
        entity.setUser_id(currentUser.getId());
        entity.setSend_mail(model.getSend_mail());
        entity.setSend_type(model.getSend_type());
        entity.setUser_maintenance(model.getUser_maintenance());
        return entity;
    }
    public void toEntity(ScheduleApi.ScheduleCreateModel model,ScheduleCalendar scheduleCalendar){
        scheduleCalendar.setStart(DateUtils.at(model.getStart()));
        scheduleCalendar.setEndAt(DateUtils.at(model.getEnd()));
        scheduleCalendar.setTitle(model.getTitle());
        scheduleCalendar.setContent(model.getContent());
        scheduleCalendar.setSend_mail(model.getSend_mail());
        scheduleCalendar.setSend_type(model.getSend_type());
        scheduleCalendar.setUser_maintenance(model.getUser_maintenance());
    }

    public ScheduleModel toModel(ScheduleCalendar entity){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ScheduleModel model = new ScheduleModel();
        model.setId(entity.getId());
        model.setTitle(entity.getTitle());
        model.setContent(entity.getContent());
        model.setStart(entity.getStart().format(formatter));
        model.setEnd(entity.getEndAt().format(formatter));
        model.setUser_maintenance(entity.getUser_maintenance());
        model.setSend_mail(entity.getSend_mail());
        model.setSend_type(entity.getSend_type());
        return model;
    }
}
