package com.dds.oee.processor;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.api.CanOng02Api;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
import com.dds.oee.entitycanong02.CanOng02ProductionOrder;
import com.dds.oee.entitycanong02.QCanOng02ProductionOrder;
import com.dds.oee.payload.CanOng01ProductionModel;
import com.dds.oee.payload.CanOng02ProductionModel;
import com.dds.oee.service.CanOng02ProductOrderService;
import com.dds.oee.transformer.CanOng02SplitMap;
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
public class CanOng02ProductionOrderProcessor extends CommonProcessor<CanOng02ProductOrderService, QCanOng02ProductionOrder, CanOng02SplitMap> {

    public CanOng02ProductionOrderProcessor(CanOng02ProductOrderService service, CanOng02SplitMap transformer) {
        super(service, QCanOng02ProductionOrder.canOng02ProductionOrder, transformer);
    }

    public CanOng02ProductionModel getCanOng02ProductionOrder() {
        CanOng02ProductionOrder canOng02ProductionOrder = service.findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(canOng02ProductionOrder);
    }

    public Page<CanOng02ProductionModel> getReportsC2(CanOng02Api.QueryModel model) {
        Pageable pageable = PageRequest.of(model.getPage(), model.getSize(), Sort.by(desc(Q.date1)));
        return service.getReportsC2(model, pageable)
                .map(transformer::toModel);
    }
}
