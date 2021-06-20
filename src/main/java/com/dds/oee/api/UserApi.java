package com.dds.oee.api;

import com.dds.oee.exception.InputInvalidException;
import com.dds.oee.payload.SignUpRequest;
import com.dds.oee.payload.UserIdentityAvailability;
import com.dds.oee.payload.UserSummary;
import com.dds.oee.processor.UserProcessor;
import com.dds.oee.security.CurrentUser;
import com.dds.oee.security.UserPrincipal;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Api(tags = "UserApi")
@RestController
@RequestMapping("/api")
public class UserApi {

	private final UserProcessor processor;

	public UserApi(UserProcessor processor) {
		this.processor = processor;
	}

	@PostMapping("/user/signup")
	public UserSummary registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws InputInvalidException {
		try {
			UserSummary userSummary = processor.signUp(signUpRequest);
			return userSummary;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	@GetMapping("/user/me")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		return processor.getCurrentUser(currentUser);
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		return processor.checkUsernameAvailability(username);
	}

	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		return processor.checkEmailAvailability(email);
	}
	@GetMapping("/user/listuser")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public List<UserSummary> listUser() throws InputInvalidException {
		return processor.getListUser();
	}
	@PostMapping("/user/deleteuser")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public UserParam  delete(@Valid @RequestBody UserParam userParam) {
		processor.deleteUser(userParam);
		return userParam;
	}
	@Setter
	@Getter
	public static class UserParam {
		private Long id;
		private String userName;
	}

}
