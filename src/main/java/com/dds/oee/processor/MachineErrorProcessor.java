package com.dds.oee.processor;

import com.dds.oee.api.MachineSplitApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QMachineSplitError;
import com.dds.oee.payload.MachineSplitErrorModel;
import com.dds.oee.service.MachineSplitErrorService;
import com.dds.oee.transformer.MachineSplitMap;
import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MachineErrorProcessor extends CommonProcessor<MachineSplitErrorService, QMachineSplitError, MachineSplitMap> {
    public MachineErrorProcessor(MachineSplitErrorService service,MachineSplitMap transformer){
        super(service,QMachineSplitError.machineSplitError,transformer);
    }

    public Page<MachineSplitErrorModel> queryError(QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::mapMachineError);
    }

    private Predicate buildCondition(QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
