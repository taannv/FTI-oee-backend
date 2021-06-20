package com.dds.oee.service;

import org.springframework.stereotype.Service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.HistoryError;
import com.dds.oee.repository.HistoryErrorRepository;

/**
 * Author : duybv
 * Sep 17, 2019
 */

@Service
public class HistoryErrorService extends CommonService<HistoryError, Long, HistoryErrorRepository> {

	public HistoryErrorService(HistoryErrorRepository repo) {
		super(repo);
	}

	@Override
	public String notFoundMessage(Long id) {
		return "Không tìm thấy lịch sử lỗi có id: " + id;
	}

}
