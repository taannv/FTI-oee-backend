package com.dds.oee.processor;

import com.dds.oee.api.MachineSplitApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QProduction;
import com.dds.oee.payload.ProductionReportVolumeModel;
import com.dds.oee.service.ProductionVolumeService;
import com.dds.oee.transformer.ProductionReportTransformer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 *
 * @author: Nguyen Van Tan
 *
 * May 12, 2020
 *
 */
@Component
public class ProductionVolumeProcessor extends CommonProcessor<ProductionVolumeService, QProduction, ProductionReportTransformer> {


    public ProductionVolumeProcessor(ProductionVolumeService service, ProductionReportTransformer transformer) {
        super(service, QProduction.production, transformer);
    }

    public Page<ProductionReportVolumeModel> getReportProductionVolume(QueryModel model) {
            Pageable pageable = PageRequest.of(model.getPage(), model.getSize(), Sort.by(desc(Q.lenh_sx)));
            return service.getReportProductionVolume(model, pageable)
                    .map(transformer::toModel);
    }

}
