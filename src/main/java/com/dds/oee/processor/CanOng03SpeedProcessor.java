package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong03.CanOng03Speed;
import com.dds.oee.entitycanong03.QCanOng03Speed;
import com.dds.oee.payload.CanOng03SpeedModel;
import com.dds.oee.service.CanOng03SpeedService;
import com.dds.oee.transformer.CanOng03SplitMap;
import org.springframework.stereotype.Component;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2031
 */
@Component
public class CanOng03SpeedProcessor extends CommonProcessor<CanOng03SpeedService, QCanOng03Speed, CanOng03SplitMap> {

    public CanOng03SpeedProcessor(CanOng03SpeedService service, CanOng03SplitMap transformer) {
        super(service, QCanOng03Speed.canOng03Speed, transformer);
    }

    public CanOng03SpeedModel getSpeed() {
        CanOng03Speed speed = service.getTopByIdDESC();
        return transformer.mapCanOng03Speed(speed);
    }
}
