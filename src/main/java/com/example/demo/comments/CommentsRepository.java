package com.example.demo.comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.articles.ArticleEntity;

@Repository
public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {
	Iterable<CommentEntity> findByArticle(ArticleEntity article);
}
