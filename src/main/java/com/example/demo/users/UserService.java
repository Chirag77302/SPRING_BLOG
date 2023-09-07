package com.example.demo.users;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.users.dtos.CreateUserRequest;

import lombok.experimental.var;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public UserEntity createUser(CreateUserRequest u) {
		UserEntity newuserEntity = modelMapper.map(u, UserEntity.class);
		newuserEntity.setPassword(passwordEncoder.encode(u.getPassword()));
		return userRepository.save(newuserEntity);					
	}
	
	public UserEntity getuser(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}
	
	public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
	
	public UserEntity loginUser(String username, String password) {
		var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
		var passmatches = passwordEncoder.matches(password, user.getPassword());
		System.out.println("passmatch check : "+passmatches);
		if(!passmatches)throw new InvalidCredentialsException();
		return user;
	}
	
	public static class UserNotFoundException extends IllegalArgumentException{
		public UserNotFoundException(String username) {
			super("User " + username + " not found");
		}
		public UserNotFoundException(Long userId) {
			super("User with id : " + userId + " not found");
		}
	}
	
	public static class InvalidCredentialsException extends IllegalArgumentException {
		public InvalidCredentialsException() {
			super("Invalid username or password combination");
		}
	}
}