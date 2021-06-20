package com.dds.oee.processor;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong01.CanOng01Split;
import com.dds.oee.entitycanong01.QCanOng01Split;
import com.dds.oee.payload.CanOng01Model;
import com.dds.oee.service.CanOng01Service;
import com.dds.oee.transformer.CanOng01SplitMap;
import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 15, 2021
 *
 */
@Component
public class CanOng01Processor extends CommonProcessor<CanOng01Service, QCanOng01Split, CanOng01SplitMap> {

    public CanOng01Processor(CanOng01Service service, CanOng01SplitMap transformer) {
        super(service, QCanOng01Split.canOng01Split, transformer);
    }

    public CanOng01Model getAllCanOng01() {
        CanOng01Split canOng01Split = service
                .findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(canOng01Split);
    }

    public Page<CanOng01Model> query(CanOng01Api.QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::toModel);
    }

    private Predicate buildCondition(CanOng01Api.QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
