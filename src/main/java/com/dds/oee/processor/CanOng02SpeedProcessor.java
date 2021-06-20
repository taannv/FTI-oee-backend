package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong02.CanOng02Speed;
import com.dds.oee.entitycanong02.QCanOng02Speed;
import com.dds.oee.payload.CanOng02SpeedModel;
import com.dds.oee.service.CanOng02SpeedService;
import com.dds.oee.transformer.CanOng02SplitMap;
import org.springframework.stereotype.Component;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2021
 */
@Component
public class CanOng02SpeedProcessor extends CommonProcessor<CanOng02SpeedService, QCanOng02Speed, CanOng02SplitMap> {

    public CanOng02SpeedProcessor(CanOng02SpeedService service, CanOng02SplitMap transformer) {
        super(service, QCanOng02Speed.canOng02Speed, transformer);
    }

    public CanOng02SpeedModel getSpeed() {
        CanOng02Speed speed = service.getTopByIdDESC();
        return transformer.mapCanOng02Speed(speed);
    }
}
