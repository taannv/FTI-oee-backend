package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong02.QCanOng02SplitStatus;
import com.dds.oee.payload.CanOng02StatusModel;
import com.dds.oee.service.CanOng02StatusService;
import com.dds.oee.transformer.CanOng02SplitMap;
import org.springframework.stereotype.Component;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Component
public class CanOng02StatusProcessor extends CommonProcessor<CanOng02StatusService, QCanOng02SplitStatus, CanOng02SplitMap> {

    public CanOng02StatusProcessor(CanOng02StatusService service, CanOng02SplitMap transformer) {
        super(service, QCanOng02SplitStatus.canOng02SplitStatus, transformer);
    }

    public CanOng02StatusModel getCanOng02Status() {
        return transformer.mapCanOng02Status(service.findTopByOrderByIdDesc());
    }
}
