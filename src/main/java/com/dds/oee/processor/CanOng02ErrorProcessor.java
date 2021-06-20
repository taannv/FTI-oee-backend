package com.dds.oee.processor;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.api.CanOng02Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong02.QCanOng02SplitError;
import com.dds.oee.payload.CanOng01SplitErrorModel;
import com.dds.oee.payload.CanOng02SplitErrorModel;
import com.dds.oee.service.CanOng02SplitErrorService;
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
public class CanOng02ErrorProcessor extends CommonProcessor<CanOng02SplitErrorService, QCanOng02SplitError, CanOng02SplitMap> {

    public CanOng02ErrorProcessor(CanOng02SplitErrorService service, CanOng02SplitMap transformer) {
        super(service, QCanOng02SplitError.canOng02SplitError, transformer);
    }

    public Page<CanOng02SplitErrorModel> queryError(CanOng02Api.QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::mapCanOng02Error);
    }

    private Predicate buildCondition(CanOng02Api.QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
