package com.dds.oee.repository;

import org.springframework.stereotype.Repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.Machine;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Repository
public interface MachineRepository extends CommonRepository<Machine, Long> {
	
}
