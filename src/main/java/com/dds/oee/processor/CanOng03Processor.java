package com.dds.oee.processor;

import com.dds.oee.api.CanOng03Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong03.CanOng03Split;
import com.dds.oee.entitycanong03.QCanOng03Split;
import com.dds.oee.payload.CanOng03Model;
import com.dds.oee.service.CanOng03Service;
import com.dds.oee.transformer.CanOng03SplitMap;
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
public class CanOng03Processor extends CommonProcessor<CanOng03Service, QCanOng03Split, CanOng03SplitMap> {

    public CanOng03Processor(CanOng03Service service, CanOng03SplitMap transformer) {
        super(service, QCanOng03Split.canOng03Split, transformer);
    }

    public CanOng03Model getAllCanOng03() {
        CanOng03Split canOng03Split = service
                .findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(canOng03Split);
    }

    public Page<CanOng03Model> query(CanOng03Api.QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::toModel);
    }

    private Predicate buildCondition(CanOng03Api.QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
