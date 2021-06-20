package com.dds.oee.processor;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.api.MachineSplitApi;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
import com.dds.oee.entitycanong01.QCanOng01ProductionOrder;
import com.dds.oee.payload.CanOng01Model;
import com.dds.oee.payload.CanOng01ProductionModel;
import com.dds.oee.payload.MachineSplitModel;
import com.dds.oee.service.CanOng01ProductOrderService;
import com.dds.oee.transformer.CanOng01SplitMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 25, 2021
 *
 */
@Component
public class CanOng01ProductionOrderProcessor extends CommonProcessor<CanOng01ProductOrderService, QCanOng01ProductionOrder, CanOng01SplitMap> {


    public CanOng01ProductionOrderProcessor(CanOng01ProductOrderService service, CanOng01SplitMap transformer) {
        super(service, QCanOng01ProductionOrder.canOng01ProductionOrder, transformer);
    }

    public CanOng01ProductionModel getCanOng01ProductionOrder() {
        CanOng01ProductionOrder canOng01ProductionOrder = service.findTopByDate1EqualsOrderByIdDesc();
        return transformer.toModel(canOng01ProductionOrder);
    }

    public Page<CanOng01ProductionModel> getReports(CanOng01Api.QueryModel model) {
        Pageable pageable = PageRequest.of(model.getPage(), model.getSize(), Sort.by(desc(Q.date1)));
        return service.getReports(model, pageable)
                .map(transformer::toModel);
    }
}
