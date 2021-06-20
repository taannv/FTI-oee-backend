package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Repository
public interface UserRepository extends CommonRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameOrEmail(String username, String email);

	List<User> findByIdIn(List<Long> userIds);
	List<User> findByIdNotIn(Long id);

	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
}
