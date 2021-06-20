package com.dds.oee.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.Role;
import com.dds.oee.entity.RoleName;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Repository
public interface RoleRepository extends CommonRepository<Role, Long> {
	
	Optional<Role> findByName(RoleName roleName);
	
}
