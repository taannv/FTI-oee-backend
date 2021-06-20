package com.dds.oee.payload;

import javax.validation.constraints.NotBlank;

/**
 * Author : duybv
 * Aug 28, 2019
 */
public class LoginRequest {

	@NotBlank
	private String usernameOrEmail;

	@NotBlank
	private String password;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest{" +
				"usernameOrEmail='" + usernameOrEmail + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
