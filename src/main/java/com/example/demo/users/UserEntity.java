package com.example.demo.users;
import org.springframework.lang.Nullable;
import org.springframework.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name="Users")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	@NonNull
	private String username;
	
	@Column(nullable = false)
	@NonNull
	private String email;
	
	@Column(nullable = true)
	@Nullable
	private String bio;
	
	@Column(name = "name",nullable = false)
	@Nullable
	private String image;
	
}
