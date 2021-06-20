package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entitycanong01.CanOng01Speed;
import com.dds.oee.entitycanong01.QCanOng01Speed;
import com.dds.oee.payload.CanOng01SpeedModel;
import com.dds.oee.service.CanOng01SpeedService;
import com.dds.oee.transformer.CanOng01SplitMap;
import org.springframework.stereotype.Component;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2021
 */
@Component
public class CanOng01SpeedProcessor extends CommonProcessor<CanOng01SpeedService, QCanOng01Speed, CanOng01SplitMap> {

    public CanOng01SpeedProcessor(CanOng01SpeedService service, CanOng01SplitMap transformer) {
        super(service, QCanOng01Speed.canOng01Speed, transformer);
    }

    public CanOng01SpeedModel getSpeed() {
        CanOng01Speed speed = service.getTopByIdDESC();
        return transformer.mapCanOng01Speed(speed);
    }
}
