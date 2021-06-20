package com.dds.oee.service;

import org.springframework.stereotype.Service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.Volume;
import com.dds.oee.repository.VolumeRepository;

/**
 * Author : duybv
 * Sep 17, 2019
 */

@Service
public class VolumeService extends CommonService<Volume, Long, VolumeRepository> {

	public VolumeService(VolumeRepository repo) {
		super(repo);
	}

	@Override
	protected String notFoundMessage(Long id) {
		return "Không tìm thấy sản lượng có id: " + id;
	}

}
