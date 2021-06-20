package com.dds.oee.processor;

import com.dds.oee.api.CanOng03Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong03.QCanOng03SplitError;
import com.dds.oee.payload.CanOng03SplitErrorModel;
import com.dds.oee.service.CanOng03SplitErrorService;
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
public class CanOng03ErrorProcessor extends CommonProcessor<CanOng03SplitErrorService, QCanOng03SplitError, CanOng03SplitMap> {

    public CanOng03ErrorProcessor(CanOng03SplitErrorService service, CanOng03SplitMap transformer) {
        super(service, QCanOng03SplitError.canOng03SplitError, transformer);
    }

    public Page<CanOng03SplitErrorModel> queryError(CanOng03Api.QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::mapCanOng03Error);
    }

    private Predicate buildCondition(CanOng03Api.QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
