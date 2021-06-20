package com.dds.oee.processor;

import com.dds.oee.api.CanOng02Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong02.CanOng02Split;
import com.dds.oee.entitycanong02.QCanOng02Split;
import com.dds.oee.payload.CanOng02Model;
import com.dds.oee.service.CanOng02Service;
import com.dds.oee.transformer.CanOng02SplitMap;
import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Component
public class CanOng02Processor extends CommonProcessor<CanOng02Service, QCanOng02Split, CanOng02SplitMap> {

    public CanOng02Processor(CanOng02Service service, CanOng02SplitMap transformer) {
        super(service, QCanOng02Split.canOng02Split, transformer);
    }

    public CanOng02Model getAllCanOng02() {
        CanOng02Split canOng02Split = service
                .findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(canOng02Split);
    }

    public Page<CanOng02Model> query(CanOng02Api.QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::toModel);
    }

    private Predicate buildCondition(CanOng02Api.QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
