package com.dds.oee.service;

import org.springframework.stereotype.Service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.Timeline;
import com.dds.oee.repository.TimelineRepository;

/**
 * Author : duybv
 * Oct 18, 2019
 */

@Service
public class TimelineService extends CommonService<Timeline, Long, TimelineRepository> {

	private TimelineService(TimelineRepository repo) {
		super(repo);
	}
	
	@Override
	protected String notFoundMessage(Long id) {
		return null;
	}

}
