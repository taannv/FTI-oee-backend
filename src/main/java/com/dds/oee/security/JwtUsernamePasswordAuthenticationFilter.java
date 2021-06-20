package com.dds.oee.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dds.oee.payload.LoginRequest;
import com.dds.oee.utils.JsonMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Author : duybv Aug 28, 2019
 */

@Slf4j
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;

        // By default, UsernamePasswordAuthenticationFilter listens to "/login" path.
        // In our case, we use "/auth". So, we need to override the defaults.
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(tokenProvider.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            log.info("c.d.o.s.JwtAuthenticationEntryPoint");
            LoginRequest loginRequest = JsonMapper.read(request.getInputStream(), LoginRequest.class);
            log.info("c.d.o.s.JwtAuthenticationEntryPoint - loginRequest: " + loginRequest);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    // Upon successful authentication, generate a token.
    // The 'auth' passed to successfulAuthentication() is the current authenticated
    // user.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        // Add token to header
        response.addHeader(tokenProvider.getHeader(), tokenProvider.getPrefix() + tokenProvider.generateToken(auth));
    }
}