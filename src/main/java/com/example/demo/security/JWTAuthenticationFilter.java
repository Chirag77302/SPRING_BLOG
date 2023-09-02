package com.example.demo.security;

import java.beans.JavaBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;

@JavaBean
public class JWTAuthenticationFilter extends AuthenticationFilter {
//	private AuthenticationManager jwtAuthenticationManager;
	private JWTAuthenticationManager jwtAuthenticationManager;
	public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
        super(jwtAuthenticationManager, new JWTAuthenticationConverter());
        
        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }
	
}
