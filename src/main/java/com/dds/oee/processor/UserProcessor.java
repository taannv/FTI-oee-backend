package com.dds.oee.processor;

import com.dds.oee.api.UserApi;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QUser;
import com.dds.oee.entity.Role;
import com.dds.oee.entity.RoleName;
import com.dds.oee.entity.User;
import com.dds.oee.exception.InputInvalidException;
import com.dds.oee.payload.SignUpRequest;
import com.dds.oee.payload.UserIdentityAvailability;
import com.dds.oee.payload.UserSummary;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.service.RoleService;
import com.dds.oee.service.UserService;
import com.dds.oee.transformer.UserTransformer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Component
public class UserProcessor extends CommonProcessor<UserService, QUser, UserTransformer> {

	private final RoleService roleService;

	public UserProcessor(UserService service, UserTransformer transformer, RoleService roleService) {
		super(service, QUser.user, transformer);
		this.roleService = roleService;
	}

	public UserIdentityAvailability checkUsernameAvailability(String userName) {
		Boolean isAvailable = !service.existsByUsername(userName);
		return UserIdentityAvailability.create(isAvailable);
	}

	public UserIdentityAvailability checkEmailAvailability(String email) {
		Boolean isAvailable = !service.existsByEmail(email);
		return UserIdentityAvailability.create(isAvailable);
	}
	
	public UserSummary getCurrentUser(UserPrincipal currentUser) {
		return UserSummary.create(currentUser.getId(), currentUser.getUsername(), currentUser.getName(),currentUser.getEmail(),
				currentUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
	}

	public UserSummary signUp(SignUpRequest signUpRequest) throws InputInvalidException {
		if (service.existsByUsername(signUpRequest.getUsername())) {
			throw InputInvalidException.create("UserName", "Tên người dùng đã được sử dụng!");
		}

		if (service.existsByEmail(signUpRequest.getEmail())) {
			throw InputInvalidException.create("Email", "Email đã được sử dụng!");
		}

		User user = create(transformer.from(signUpRequest), RoleName.ROLE_USER);

		return UserSummary.create(user.getId(), user.getUsername(), user.getName(), user.getEmail());
	}
	
	private User create(User user, RoleName roleName) {
		Role role = roleService.findByNameOrElseThrow(RoleName.ROLE_USER);

		user.addRole(role);

		return service.save(user);
	}
	public List<UserSummary> getListUser(){
		return service.findAllUser().stream().map(transformer::toModel)
				.collect(Collectors.toList());
	}
	public void deleteUser(UserApi.UserParam currentUser){
		User user = service.findById(currentUser.getId());
		service.delete(user);
	}
}
