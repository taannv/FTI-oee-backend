package com.dds.oee.processor;

import com.dds.oee.api.MachineSplitApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.ProductionOrder;
import com.dds.oee.entity.QProductionOrder;
import com.dds.oee.payload.MachineSplitModel;
import com.dds.oee.service.ProductOrderService;
import com.dds.oee.transformer.MachineSplitMap;
import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class ProductionOrderProcessor extends CommonProcessor<ProductOrderService, QProductionOrder, MachineSplitMap> {

    public ProductionOrderProcessor(ProductOrderService service, MachineSplitMap transformer) {
        super(service, QProductionOrder.productionOrder, transformer);
    }

    public Page<MachineSplitModel> queryProductionOrder(QueryModel model) {
        return service.query(conditionProduction(model), model.getPage(), model.getSize(), desc(Q.date1))
                .map(transformer::toModel);
    }

    public MachineSplitModel getProductionOrder() {
        ProductionOrder productionOrder = service.findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(productionOrder);
    }

    public Page<MachineSplitModel> getReports(QueryModel model) {
        Pageable pageable = PageRequest.of(model.getPage(), model.getSize(), Sort.by(desc(Q.date1)));
        return service.getReports(model, pageable)
                .map(transformer::toModel);
    }

    private Predicate conditionProduction(QueryModel model) {
        BooleanBuilder condition = new BooleanBuilder();
        if (model.getFromDate() != null && model.getToDate() != null) {
            condition.and(Q.date1.between(DateUtils.atStartOfDay(model.getFromDate()), DateUtils.atStartOfDay(model.getToDate())));
        }
        if (model.getHFrom() != null && model.getHTo() != null) {
            condition.and(Q.do_day.between(model.getHFrom(), model.getHTo()));
        }
        if (model.getWFrom() != null && model.getWTo() != null) {
            condition.and(Q.chieu_rong.between(model.getWFrom(), model.getWTo()));
        }
        return condition;
    }
}
