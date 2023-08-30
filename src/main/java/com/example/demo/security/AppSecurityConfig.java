package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//import jakarta.security.auth.message.config.AuthConfig;

@Configuration
//@EnableMethodSecurity
public class AppSecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		RequestMatcher requestMatcher = new AntPathRequestMatcher("/*", "GET");
		RequestMatcher requestMatcher2 = new AntPathRequestMatcher("/users", "POST");
		RequestMatcher requestMatcher3 = new AntPathRequestMatcher("/users/login", "POST");
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(requestMatcher2,requestMatcher3).permitAll()
//					.requestMatchers(requestMatcher3).permitAll()
					.anyRequest().authenticated());
		
		return http.build();
	  }
	
}
