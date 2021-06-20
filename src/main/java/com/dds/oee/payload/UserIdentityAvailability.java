package com.dds.oee.payload;

/**
 * Author : duybv
 * Aug 28, 2019
 */

public class UserIdentityAvailability {

	private Boolean available;

	private UserIdentityAvailability() {};

	private UserIdentityAvailability(Boolean available) {
		this.available = available;
	}

	public static UserIdentityAvailability create(Boolean available) {
		return new UserIdentityAvailability(available);
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
