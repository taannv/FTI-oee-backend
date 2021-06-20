package com.dds.oee.payload;

import org.springframework.validation.BindingResult;

import lombok.Getter;

/**
* Author : duybv
* Sep 17, 2019
*/

@Getter
public class ErrorDetail {

	private String errorField;
	private String error;
	private String message;
	
	private ErrorDetail(String errorField, String error, String message) {
		this.errorField = errorField;
		this.error = error;
		this.message = message;
	}
	
	public static ErrorDetail create(String errorField, String error, String message) {
		return new ErrorDetail(errorField, error, message);
	}
	
	public static ErrorDetail create(BindingResult bindingResult, String message) {
		return new ErrorDetail(bindingResult.getFieldError().getField(), bindingResult.getFieldError().getDefaultMessage(), message);
	}

}
