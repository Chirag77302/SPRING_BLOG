package com.example.demo.users;

import java.net.URI;
import java.util.Iterator;

import javax.lang.model.element.ModuleElement;

import org.checkerframework.checker.interning.qual.FindDistinct;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commons.dto.ErrorResponse;
import com.example.demo.users.UserService.UserNotFoundException;
import com.example.demo.users.dtos.CreateUserLoginRequest;
import com.example.demo.users.dtos.CreateUserRequest;
import com.example.demo.users.dtos.UserResponse;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@PostMapping("")
	ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest req) {
//		System.out.println("req body is"+req);
		UserEntity su = userService.createUser(req);
		URI savedusUri = URI.create("/users/" + su.getId());
		return ResponseEntity.created(savedusUri).body(modelMapper.map(su, UserResponse.class));
	}
	
	@PostMapping("/login")
	ResponseEntity<UserResponse> loginUser(@RequestBody CreateUserLoginRequest req) {
//		System.out.println("entered the login route");
		UserEntity savedUser = userService.loginUser(req.getUsername(), req.getPassword());
		return ResponseEntity.ok(modelMapper.map(savedUser, UserResponse.class));
	}
	
	@ExceptionHandler({
		UserService.UserNotFoundException.class,
		UserService.InvalidCredentialsException.class
	})
	ResponseEntity<ErrorResponse> handleUserExceptions(Exception ex){
		String message;
		HttpStatus status; 
		if(ex instanceof UserService.UserNotFoundException) {
			message = ex.getMessage();
			status = HttpStatus.NOT_FOUND;
		}
		else if(ex instanceof UserService.InvalidCredentialsException) {
			message = ex.getMessage();
			status = HttpStatus.UNAUTHORIZED;
		}
		else {
			message = "Something went wrong";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return ResponseEntity
				.status(status)
				.body(ErrorResponse.builder()
						.message(message)
						.build()
						);
	}
}
