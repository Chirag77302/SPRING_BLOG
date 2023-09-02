package com.example.demo.security;

import java.util.Date;

//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

import lombok.experimental.var;
import net.bytebuddy.asm.Advice.Return;

@Service
public class JWTService {
	
	private static final String JWT_STRING = "dfaugekueqwhvgdbasaliud278136162401692092147";
	private final Algorithm algorithm = Algorithm.HMAC256(JWT_STRING);
	
	public String createJWT(Long id) {
		return JWT.create()
				.withSubject(id.toString())
				.withIssuedAt(new Date())
				.sign(algorithm);
	}
	
	public Long retrieveUserid(String jwtString) {
			var decodedJWT = JWT.decode(jwtString);
			var usedid = Long.valueOf(decodedJWT.getSubject());
			return usedid;
	}
}
