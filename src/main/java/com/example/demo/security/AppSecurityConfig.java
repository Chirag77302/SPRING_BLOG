package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import com.example.demo.users.UserService;

//import jakarta.security.auth.message.config.AuthConfig;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	private JWTService jwtService;
	private UserService userService;
//	private JWTAuthenticationFilter jwtAuthenticationFilter;
//	
	public AppSecurityConfig(JWTService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
//		this.jwtAuthenticationFilter = new JWTAuthenticationFilter(
//                new JWTAuthenticationManager(jwtService, userService));;
	}

	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter(){
		return new JWTAuthenticationFilter(new JWTAuthenticationManager(jwtService, userService));
	}


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		RequestMatcher requestMatcher1 = new AntPathRequestMatcher("/articles/*", "GET");
		RequestMatcher requestMatcher2 = new AntPathRequestMatcher("/users", "POST");
		RequestMatcher requestMatcher3 = new AntPathRequestMatcher("/users/login", "POST");
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(requestMatcher1,requestMatcher2,requestMatcher3).permitAll()
					.anyRequest().authenticated());
		
		http.addFilterBefore(jwtAuthenticationFilter(),AnonymousAuthenticationFilter.class);
		return http.build();
	  }
	
}
