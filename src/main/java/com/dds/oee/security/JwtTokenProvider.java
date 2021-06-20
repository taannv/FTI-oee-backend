package com.dds.oee.security;

import com.dds.oee.utils.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Component
@Log4j2 @Getter
public class JwtTokenProvider {
	
	@Value("${security.jwt.uri:/api/auth/**}")
    private String Uri;

	@Value("${security.jwt.secret:oee-app}")
    private String secret;

	@Value("${security.jwt.prefix:Bearer }")
	private String prefix;

	@Value("${security.jwt.header:Authorization}")
	private String header;

	public String generateToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(Long.toString(userPrincipal.getId()))
				.claim("authorities", userPrincipal.getAuthorities())
				.setIssuedAt(DateUtils.currentTime())
				.setExpiration(DateUtils.endOfCurrentDay()) // in milliseconds
				.signWith(SignatureAlgorithm.HS512, getSecret())
				.compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(getSecret())
				.parseClaimsJws(token)
				.getBody();

		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(getSecret()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
}
