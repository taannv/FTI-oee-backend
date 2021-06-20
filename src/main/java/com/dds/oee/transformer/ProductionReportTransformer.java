package com.dds.oee.transformer;

import com.dds.oee.entity.Production;
import com.dds.oee.payload.ProductionReportVolumeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author: Nguyen Van Tan
 *
 * May 13, 2020
 *
 */
@Component
public class ProductionReportTransformer {

    public List<ProductionReportVolumeModel> toModels(List<Production> entities) {
        return entities.stream().map(this::toModel).collect(Collectors.toList());
    }

    public ProductionReportVolumeModel toModel(Production entity) {
        ProductionReportVolumeModel productionReportVolumeModel = new ProductionReportVolumeModel();

        productionReportVolumeModel.setProductionOrder(entity.getLenh_sx());
        productionReportVolumeModel.setMass(entity.getKhoi_luong());

        return productionReportVolumeModel;
    }
}
