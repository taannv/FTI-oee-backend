package com.dds.oee.processor;

import com.dds.oee.api.MachineSplitApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QProductionOrder;
import com.dds.oee.payload.ProductChartModel;
import com.dds.oee.service.ProductionByMonthService;
import com.dds.oee.transformer.MachineSplitMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Nguyen Van Tan
 *
 * May 20, 2020
 **/

@Component
public class ReportProductionByMonthProcessor extends CommonProcessor<ProductionByMonthService, QProductionOrder, MachineSplitMap> {

    public ReportProductionByMonthProcessor(ProductionByMonthService service, MachineSplitMap transformer) {
        super(service, QProductionOrder.productionOrder, transformer);
    }

    public Page<ProductChartModel> getProductionByMonth(QueryModel model) {
        try {
            Pageable pageable = PageRequest.of(model.getPage(), model.getSize(), Sort.by(desc(Q.ngay)));
            try {
                return service.getProductionByMonth(model, pageable)
                        .map(transformer::toModelProductionByMonth);
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("=============33333================");
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("=============44444================");
        }


       return null;
    }
}
