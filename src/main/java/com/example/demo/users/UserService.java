package com.example.demo.users;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.users.dtos.CreateUserRequest;

import lombok.experimental.var;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public UserEntity createUser(CreateUserRequest u) {
		var user = UserEntity.builder().username(u.getUsername()).email(u.getEmail()).build();
		return userRepository.save(user);					
	}
	
	public UserEntity getuser(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
	}
	
	public UserEntity getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
	
	public UserEntity loginUser(String username, String password) {
		var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
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
	
}
