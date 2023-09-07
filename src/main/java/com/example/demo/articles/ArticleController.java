package com.example.demo.articles;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.articles.dtos.ArticleResponse;
import com.example.demo.articles.dtos.CreateArticleRequest;
import com.example.demo.users.UserEntity;
import com.example.demo.users.dtos.CreateUserRequest;

import jakarta.validation.constraints.AssertFalse.List;
import lombok.var;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	private final ArticleService articleService;
	private final ModelMapper modelMapper;
	public ArticleController(ArticleService articleService,ModelMapper modelMapper) {
		this.articleService = articleService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping("")
    Iterable<ArticleEntity> getArticles() {
		var allarticles = articleService.getallArticles();
//		System.out.println(allarticles);
		return allarticles;
    }

    @GetMapping("/{id}")
    ResponseEntity<ArticleResponse> getArticleById(@PathVariable("id") String id) {
        ArticleEntity article = articleService.getarticlebyid(Long.parseLong(id));
        var articleresponse = modelMapper.map(article, ArticleResponse.class);
        return ResponseEntity.ok(articleresponse);
    }
    
//    @PostMapping("")
//    String createArticle(@AuthenticationPrincipal UserEntity user) {
//        return "create article called by " + user.getUsername();
//    }
    
    @PostMapping("")
	 ResponseEntity<ArticleResponse> createArticle(@AuthenticationPrincipal UserEntity user,@RequestBody CreateArticleRequest art) {
	        System.out.println("entered here");
//	        System.out.println("article title is : " + art.getTitle() + " " + art.getBody() + " " + art.getSubtitle());
	    	Long userid = user.getId();
	        System.out.println(userid);
	        ArticleEntity created_article = articleService.createArticle(art, userid);
//	        System.out.println("created article is : " + created_article);
	        URI savedArticleUri = URI.create("/articles" + created_article.getId());
	    	var article_response = modelMapper.map(created_article, ArticleResponse.class);
	        return ResponseEntity.ok(article_response);
	 }

}
