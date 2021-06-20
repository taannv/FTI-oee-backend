package com.dds.oee.repository;

import org.springframework.stereotype.Repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.Timeline;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Repository
public interface TimelineRepository extends CommonRepository<Timeline, Long> {
	
}
