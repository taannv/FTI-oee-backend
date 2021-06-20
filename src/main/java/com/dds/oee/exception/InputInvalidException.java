package com.dds.oee.exception;

import lombok.Getter;

/**
 * Author : duybv
 * Aug 28, 2019
 */

public class InputInvalidException extends RuntimeException {

	private static final long serialVersionUID = -8505785301364089778L;

	@Getter
	private String errorField;

	private InputInvalidException(String errorField, String message) {
		super(message);
		this.errorField = errorField;
	}

	public static InputInvalidException create(String errorField, String message) {
		return new InputInvalidException(errorField, message);
	}

}
