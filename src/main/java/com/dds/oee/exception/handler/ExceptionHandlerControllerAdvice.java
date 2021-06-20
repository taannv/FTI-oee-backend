package com.dds.oee.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.dds.oee.exception.AccessDeniedException;
import com.dds.oee.exception.DuplicatedException;
import com.dds.oee.exception.InputInvalidException;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.payload.ErrorDetail;

import lombok.extern.slf4j.Slf4j;

/**
 * Author : duybv
 * Feb 24, 2019
 */

@ControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {

	private static final String INTERNAL_ERROR_MESSAGE = "Lỗi không xác định. Vui lòng liên hệ kỹ thuật để được hỗ trợ!";

	@ExceptionHandler(NotFoundEntityException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ErrorDetail handleNotFound(NotFoundEntityException e, WebRequest request) {
		return ErrorDetail.create(e.getErrorField(), e.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(DuplicatedException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public @ResponseBody ErrorDetail handleConflict(DuplicatedException e, WebRequest request) {
		return ErrorDetail.create(e.getErrorField(), e.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public @ResponseBody ErrorDetail handleForbidden(AccessDeniedException e, WebRequest request) {
		return ErrorDetail.create(e.getErrorField(), e.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(InputInvalidException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	public @ResponseBody ErrorDetail handleNotAcceptable(InputInvalidException e, WebRequest request) {
		return ErrorDetail.create(e.getErrorField(), e.getMessage(), request.getDescription(false));
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorDetail handle(RuntimeException e, WebRequest request) {
		Exception cause = (Exception) e.getCause();
		String error = cause != null ? cause.getMessage() : INTERNAL_ERROR_MESSAGE;
		return ErrorDetail.create(null, error, request.getDescription(false));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorDetail handleInternal(Exception e, WebRequest request) {
		log.error(e.getMessage(), e);
		return ErrorDetail.create(null, INTERNAL_ERROR_MESSAGE, request.getDescription(false));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(BAD_REQUEST)
	public @ResponseBody ErrorDetail handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest r) {
		if (e.getBindingResult() != null) {
			return ErrorDetail.create(e.getBindingResult(), r.getDescription(false));
		}
		return ErrorDetail.create(null, null, r.getDescription(false));
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(BAD_REQUEST)
	public @ResponseBody ErrorDetail handleBind(BindException e, WebRequest r) {
		if (e.getBindingResult() != null) {
			return ErrorDetail.create(e.getBindingResult(), r.getDescription(false));
		}
		return ErrorDetail.create(null, null, r.getDescription(false));
	}
}
