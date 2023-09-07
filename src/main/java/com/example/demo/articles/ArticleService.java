package com.example.demo.articles;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.articles.dtos.CreateArticleRequest;
import com.example.demo.articles.dtos.UpdateArticleRequest;
import com.example.demo.users.UserRepository;
import com.example.demo.users.UserService;

import lombok.experimental.var;

@Service
public class ArticleService {
	
	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	
	public ArticleService(ArticleRepository articleRepository, UserRepository userRepository,ModelMapper modelMapper) {
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	
	public Iterable<ArticleEntity> getallArticles(){
		return articleRepository.findAll();
	}
	
	public ArticleEntity getArticleBySlug(String slug) {
			var article = articleRepository.findBySlug(slug);
			if(article == null)throw new ArticleNotFoundException(slug);
			return article;
	}
	
	public ArticleEntity getarticlebyid(Long id) {
		return articleRepository.findById(id).orElseThrow(() -> new ArticleNotFoundException(id));
	}
	
	public ArticleEntity createArticle(CreateArticleRequest a, Long authorid) {
		var author = userRepository.findById(authorid).orElseThrow(() -> new UserService.UserNotFoundException(authorid));
		ArticleEntity articleEntity = modelMapper.map(a, ArticleEntity.class);
		articleEntity.setAuthor(author);
		return articleRepository.save(articleEntity);
	}
	
	public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest a) {
		var article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
		if(a.getTitle() != null) {
			article.setTitle(a.getTitle());
			article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
		}
		if(a.getBody() != null) {
			article.setBody(a.getBody());
		}
		if(a.getSubtitle() != null) {
			article.setSubtitle(a.getSubtitle());
		}
		return article;
	}

	static class ArticleNotFoundException extends IllegalArgumentException{
		public ArticleNotFoundException(String slug) {
			super("Article with slug : " + slug + " not found");
			// TODO Auto-generated constructor stub
		}
		public ArticleNotFoundException(Long articleid) {
			super("Article with id : " + articleid + " not found");
			// TODO Auto-generated constructor stub
		}
	}
}
