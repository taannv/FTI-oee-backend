package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong01.QCanOng01SplitStatus;
import com.dds.oee.payload.CanOng01StatusModel;
import com.dds.oee.service.CanOng01StatusService;
import com.dds.oee.transformer.CanOng01SplitMap;
import org.springframework.stereotype.Component;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Component
public class CanOng01StatusProcessor extends CommonProcessor<CanOng01StatusService, QCanOng01SplitStatus, CanOng01SplitMap> {

    public CanOng01StatusProcessor(CanOng01StatusService service, CanOng01SplitMap transformer) {
        super(service, QCanOng01SplitStatus.canOng01SplitStatus, transformer);
    }

    public CanOng01StatusModel getCanOng01Status() {
        return transformer.mapCanOng01Status(service.findTopByOrderByIdDesc());
    }
}
