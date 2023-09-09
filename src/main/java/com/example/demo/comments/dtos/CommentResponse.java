package com.example.demo.comments.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentResponse {
	@NotNull
	private String title;
	@NotNull
	private String body;
	@NotNull
	private Date createdAt;
}
