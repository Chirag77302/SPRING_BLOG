package com.example.demo.comments;

import java.util.Date;

import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.example.demo.articles.ArticleEntity;
import com.example.demo.users.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name="comments")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;
	
	@Nullable
	private String title;
	
	@NonNull
	private String body;
	
	@CreatedDate
	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "articleId",nullable = false)
	private ArticleEntity article;
	
	@ManyToOne
	@JoinColumn(name="authorId",nullable = false)
	private UserEntity user;
}
