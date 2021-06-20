package com.dds.oee.processor;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.api.MachineSplitApi;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong01.QCanOng01SplitError;
import com.dds.oee.payload.CanOng01SplitErrorModel;
import com.dds.oee.payload.MachineSplitErrorModel;
import com.dds.oee.service.CanOng01SplitErrorService;
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
 * January 26, 2021
 *
 */
@Component
public class CanOng01ErrorProcessor extends CommonProcessor<CanOng01SplitErrorService, QCanOng01SplitError, CanOng01SplitMap> {

    public CanOng01ErrorProcessor(CanOng01SplitErrorService service, CanOng01SplitMap transformer) {
        super(service, QCanOng01SplitError.canOng01SplitError, transformer);
    }

    public Page<CanOng01SplitErrorModel> queryError(CanOng01Api.QueryModel model){
        return service.query(buildCondition(model),model.getPage(),model.getSize(),desc(Q.date1))
                .map(transformer::mapCanOng01Error);
    }

    private Predicate buildCondition(CanOng01Api.QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()),DateUtils.atStartOfDay(model.getToDate())));
        }
        return condition;
    }
}
