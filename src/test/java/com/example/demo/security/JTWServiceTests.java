package com.example.demo.security;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;
import org.junit.jupiter.api.Test;

import lombok.var;

public class JTWServiceTests {
	
	JWTService jwtService = new JWTService();
	
	@Test
	void cancreateJWTfromuserID() {
		var jwt = jwtService.createJWT(1001L);
		assertNotNull(jwt);
	}
}
