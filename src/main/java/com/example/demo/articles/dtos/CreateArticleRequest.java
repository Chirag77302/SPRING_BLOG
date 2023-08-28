package com.example.demo.articles.dtos;

import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {
	
	@NotNull
	private String title;
	@NotNull
	private String body;
	@Nullable
	private String subtitle;
}
