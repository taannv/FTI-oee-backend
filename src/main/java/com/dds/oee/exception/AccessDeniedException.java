package com.dds.oee.exception;

import lombok.Getter;

/**
 * Author : duybv
 * Mar 28, 2019
 */
public class AccessDeniedException extends RuntimeException {

	private static final long serialVersionUID = -6129951984467636499L;

	@Getter
	private String errorField;

	public AccessDeniedException(String errorField, String message) {
		super(message);
		this.errorField = errorField;
	}

}
