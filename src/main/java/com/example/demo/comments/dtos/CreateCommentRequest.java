package com.example.demo.comments.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentRequest {
	@NotNull
	private String title;
	@NotNull
	private String body;
}
