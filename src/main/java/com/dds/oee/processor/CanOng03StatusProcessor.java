package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong03.QCanOng03SplitStatus;
import com.dds.oee.payload.CanOng03StatusModel;
import com.dds.oee.service.CanOng03StatusService;
import com.dds.oee.transformer.CanOng03SplitMap;
import org.springframework.stereotype.Component;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2031
 */
@Component
public class CanOng03StatusProcessor extends CommonProcessor<CanOng03StatusService, QCanOng03SplitStatus, CanOng03SplitMap> {

    public CanOng03StatusProcessor(CanOng03StatusService service, CanOng03SplitMap transformer) {
        super(service, QCanOng03SplitStatus.canOng03SplitStatus, transformer);
    }

    public CanOng03StatusModel getCanOng03Status() {
        return transformer.mapCanOng03Status(service.findTopByOrderByIdDesc());
    }
}
