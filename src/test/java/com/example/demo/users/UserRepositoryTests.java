package com.example.demo.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.experimental.var;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@Order(1)
	void can_create_users() {
		var user = new UserEntity();
		
//		var user = UserEntity.builder()
//						.username("cj")
//						.email("cj@blog.com")
//						.build();
//		
		userRepository.save(user);
	}
	
	@Test
	@Order(2)
	void can_find_users() {
		var users = userRepository.findAll();
		org.junit.jupiter.api.Assertions.assertEquals(1,users.size());
			
	}
}
