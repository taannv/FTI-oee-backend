package com.dds.oee.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Component
@Log4j2
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
			throws IOException, ServletException {
		log.error("Responding with unauthorized error. Message - {}", e.getMessage());
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
	}
}
