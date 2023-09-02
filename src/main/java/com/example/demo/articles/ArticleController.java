package com.example.demo.articles;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.users.UserEntity;

import jakarta.validation.constraints.AssertFalse.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	private final ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@GetMapping("")
    String getArticles() {
        return "get all articles";
    }

    @GetMapping("/{id}")
    String getArticleById(@PathVariable("id") String id) {
        return "get article with id: " + id;
    }

    @PostMapping("")
    String createArticle(@AuthenticationPrincipal UserEntity user) {
        return "create article called by " + user.getUsername();
    }
	
	
}
