package com.dds.oee.transformer;

import com.dds.oee.payload.UserSummary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dds.oee.entity.User;
import com.dds.oee.payload.SignUpRequest;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Component
public class UserTransformer {

	private final PasswordEncoder passwordEncoder;

	public UserTransformer(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public User from(SignUpRequest request) {
		User user = User.create(request.getName(), request.getUsername(), request.getEmail(), request.getPassword());

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return user;
	}
	public UserSummary toModel(User entity){
		return UserSummary.create(entity.getId(),entity.getUsername(),entity.getName(),entity.getEmail());
	}
}
