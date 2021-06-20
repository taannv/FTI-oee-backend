package com.dds.oee.service;

import org.springframework.stereotype.Service;

import com.dds.oee.entity.Role;
import com.dds.oee.entity.RoleName;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.RoleRepository;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Service
public class RoleService {

	private final RoleRepository repo;

	public RoleService(RoleRepository repo) {
		this.repo = repo;
	}

	public Role findByNameOrElseThrow(RoleName roleName) {
		return repo.findByName(roleName)
				.orElseThrow(NotFoundEntityException.ofSupplier("Role", "Không tìm thấy vai trò với name: " + roleName));
	}

}
