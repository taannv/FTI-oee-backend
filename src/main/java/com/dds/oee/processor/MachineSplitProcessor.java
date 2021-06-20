package com.dds.oee.processor;

import com.dds.oee.api.MachineSplitApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.MachineSplit;
import com.dds.oee.entity.ProductionOrder;
import com.dds.oee.entity.QMachineSplit;
import com.dds.oee.payload.MachineSplitModel;
import com.dds.oee.service.MachineSplitService;
import com.dds.oee.transformer.MachineSplitMap;
import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class MachineSplitProcessor extends CommonProcessor<MachineSplitService, QMachineSplit, MachineSplitMap> {

    public MachineSplitProcessor(MachineSplitService service, MachineSplitMap transformer) {
        super(service, QMachineSplit.machineSplit, transformer);
    }

    public MachineSplitModel getAllMachineSplit() {
        MachineSplit machineSplit = service
                .findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(machineSplit);
    }

    public Page<MachineSplitModel> query(QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::toModel);
    }

    private Predicate buildCondition(QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }

}
