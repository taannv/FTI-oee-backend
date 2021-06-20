package com.dds.oee.service;

import org.springframework.stereotype.Service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.Machine;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.MachineRepository;

/**
 * Author : duybv
 * Sep 16, 2019
 */

@Service
public class MachineService extends CommonService<Machine, Long, MachineRepository> {

	public MachineService(MachineRepository repo) {
		super(repo);
	}

	public Machine loadOrElseThrows(Long id) throws NotFoundEntityException {
		return repo.findById(id)
				.orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage(id)));
	}

	@Override
	protected String notFoundMessage(Long id) {
		return "Không tìm thấy máy phù hợp với id: " + id;
	}

}
