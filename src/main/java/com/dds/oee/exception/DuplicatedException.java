package com.dds.oee.exception;

import lombok.Getter;

/**
 * Author : duybv
 * Aug 28, 2019
 */

public class DuplicatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8169469369711746252L;
	
	@Getter
	private String errorField;

	public DuplicatedException(String errorField, String message) {
		super(message);
		this.errorField = errorField;
	}
}
