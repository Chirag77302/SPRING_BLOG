package com.example.demo.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationConverter;

import jakarta.servlet.http.HttpServletRequest;
import lombok.var;

public class JWTAuthenticationConverter implements AuthenticationConverter {

	@Override
	public Authentication convert(HttpServletRequest request) {
		
		var authheader = request.getHeader("Authorization");
		if(authheader == null || !authheader.startsWith("Bearer ")) {
			return null;
		}
		
		var jwt = authheader.substring(7);
		return new JWTAuthentication(jwt);
	}

}
