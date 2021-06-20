package com.dds.oee.security;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.dds.oee.entity.User;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.service.UserService;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserService userService;

	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws NotFoundEntityException {
		// Let people login with either username or email
		User user = userService.findByUsernameOrEmailOrElseThrow(usernameOrEmail);
		return UserPrincipal.create(user);
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = userService.getOrElseThrow(id);
		return UserPrincipal.create(user);
	}
}