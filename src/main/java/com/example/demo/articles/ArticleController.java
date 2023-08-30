package com.example.demo.articles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.AssertFalse.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	private final ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
//
//	@GetMapping("")
//	ResponseEntity<List> getallarticles() {
//		
//	}
	
	
}
