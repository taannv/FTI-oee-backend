package com.dds.oee.repository;

import org.springframework.stereotype.Repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.Volume;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Repository
public interface VolumeRepository extends CommonRepository<Volume, Long> {
	
}
