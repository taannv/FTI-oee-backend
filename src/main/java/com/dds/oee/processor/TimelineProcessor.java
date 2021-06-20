package com.dds.oee.processor;

import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QTimeline;
import com.dds.oee.service.TimelineService;
import com.dds.oee.transformer.TimelineTransformer;
import org.springframework.stereotype.Component;

/**
 * Author : duybv
 * Oct 18, 2019
 */

@Component
public class TimelineProcessor extends CommonProcessor<TimelineService, QTimeline, TimelineTransformer> {

	public TimelineProcessor(TimelineService service, TimelineTransformer transformer) {
		super(service, QTimeline.timeline, transformer);
	}

}
