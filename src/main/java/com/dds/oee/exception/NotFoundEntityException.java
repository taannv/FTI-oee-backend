package com.dds.oee.exception;

import java.util.function.Supplier;

import lombok.Getter;

/**
 * Author : duybv
 * Aug 28, 2019
 */

public class NotFoundEntityException extends RuntimeException {

	private static final long serialVersionUID = -718639735490655218L;

	@Getter
	private String errorField;

	private NotFoundEntityException(String errorField, String message) {
		super(message);
		this.errorField = errorField;
	}

	public static NotFoundEntityException create(String errorField, String message) {
		return new NotFoundEntityException(errorField, message);
	}

	public static Supplier<NotFoundEntityException> ofSupplier(String message) {
		return ofSupplier(null, message);
	}

	public static Supplier<NotFoundEntityException> ofSupplier(String errorField, String message) {
		return () -> create(errorField, message);
	}
}