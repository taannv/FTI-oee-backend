package com.dds.oee.processor;

import com.dds.oee.api.ScheduleApi;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QScheduleCalendar;
import com.dds.oee.entity.ScheduleCalendar;
import com.dds.oee.exception.InputInvalidException;
import com.dds.oee.payload.ScheduleModel;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.service.ScheduleService;
import com.dds.oee.transformer.ScheduleTransformer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleProcessor extends CommonProcessor<ScheduleService, QScheduleCalendar, ScheduleTransformer> {

    public ScheduleProcessor(ScheduleService service, ScheduleTransformer transformer) {
        super(service, QScheduleCalendar.scheduleCalendar, transformer);
    }

    public ScheduleModel create(ScheduleApi.ScheduleCreateModel model, UserPrincipal currentUser) {
        if (model.getStart() == null || model.getEnd() == null || model.getStart() > model.getEnd()) {
            throw InputInvalidException.create("", "Thời gian bắt đầu & kết thúc không phù hợp");
        }
        ScheduleCalendar entity = service.save(transformer.toEntity(model, currentUser));
        return transformer.toModel(entity);
    }

    public ScheduleModel update(Long id,ScheduleApi.ScheduleCreateModel model, UserPrincipal currentUser){
        if (model.getStart() == null || model.getEnd() == null || model.getStart() > model.getEnd()) {
            throw InputInvalidException.create("", "Thời gian bắt đầu & kết thúc không phù hợp");
        }
        ScheduleCalendar entity = service.findById(id);
        transformer.toEntity(model,entity);
        service.save(entity);
        return transformer.toModel(entity);
    }

    public List<ScheduleModel> getListSchedule() {
        return service.findAllSchedule().stream()
                .map(transformer::toModel).collect(Collectors.toList());
    }
}
