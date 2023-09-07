package com.example.demo.articles.dtos;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class UpdateArticleRequest {
	@Nullable
	private String title;
	@Nullable
	private String body;
	@Nullable
	private String subtitle;
}