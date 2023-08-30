package com.example.demo.users;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.users.dtos.CreateUserRequest;

@SpringBootTest
@ActiveProfiles("tests")
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@Test
	void can_create_users() {
//		var user = userService.createUser(new CreateUserRequest(
//                "john",
//                "pass123",
//                "john@blog.com"
//        ));
//
//		Assertions.assertNotNull(user);
//		Assertions.assertEquals("chirag", user.getUsername());
	}
}
