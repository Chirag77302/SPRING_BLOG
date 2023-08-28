package com.example.demo.users.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateUserRequest {

	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	private String email;
}
