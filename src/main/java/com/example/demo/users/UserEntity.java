package com.example.demo.users;
import org.springframework.lang.Nullable;
import org.springframework.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Builder
@Getter
@Setter
//@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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
	private String password;
	
	@Column(nullable = false)
	@NonNull
	private String email;
	
	@Column(nullable = true)
	@Nullable
	private String bio;
	
	@Column(nullable = true)
	@Nullable
	private String image;

}
