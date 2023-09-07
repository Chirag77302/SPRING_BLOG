package com.example.demo.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.users.UserService;

import lombok.var;
import net.bytebuddy.asm.Advice.Return;

public class JWTAuthenticationManager implements AuthenticationManager {
	
	private final JWTService jwtService;
	private final UserService userService;
	
	public JWTAuthenticationManager(JWTService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		if(authentication instanceof JWTAuthentication) {
			var jwtAuthentication = (JWTAuthentication) authentication;
			var jwt = jwtAuthentication.getCredentials();
			var userId = jwtService.retrieveUserid(jwt);
			var userentity = userService.getUser(userId);
			jwtAuthentication.userEntity = userentity;
			jwtAuthentication.setAuthenticated(true);
//			SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
			return jwtAuthentication;
		}
		throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
	}
	

}
