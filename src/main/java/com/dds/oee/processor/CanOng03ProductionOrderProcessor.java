package com.dds.oee.processor;

import com.dds.oee.api.CanOng03Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong03.CanOng03ProductionOrder;
import com.dds.oee.entitycanong03.QCanOng03ProductionOrder;
import com.dds.oee.payload.CanOng03ProductionModel;
import com.dds.oee.service.CanOng03ProductOrderService;
import com.dds.oee.transformer.CanOng03SplitMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Component
public class CanOng03ProductionOrderProcessor extends CommonProcessor<CanOng03ProductOrderService, QCanOng03ProductionOrder, CanOng03SplitMap> {

    public CanOng03ProductionOrderProcessor(CanOng03ProductOrderService service, CanOng03SplitMap transformer) {
        super(service, QCanOng03ProductionOrder.canOng03ProductionOrder, transformer);
    }

    public CanOng03ProductionModel getCanOng03ProductionOrder() {
        CanOng03ProductionOrder canOng03ProductionOrder = service.findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(canOng03ProductionOrder);
    }

    public Page<CanOng03ProductionModel> getReportsC3(CanOng03Api.QueryModel model) {
        Pageable pageable = PageRequest.of(model.getPage(), model.getSize(), Sort.by(desc(Q.date1)));
        return service.getReportsC3(model, pageable)
                .map(transformer::toModel);
    }
}
