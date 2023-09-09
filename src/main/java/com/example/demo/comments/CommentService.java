package com.example.demo.comments;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.demo.articles.ArticleRepository;
import com.example.demo.articles.ArticleService;
import com.example.demo.comments.dtos.CreateCommentRequest;
import com.example.demo.users.UserRepository;
import com.example.demo.users.UserService;

import lombok.experimental.var;

@Service
public class CommentService {
	private final CommentsRepository commentsRepository;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final ArticleRepository articleRepository;
	
	public CommentService(CommentsRepository commentsRepository, ModelMapper modelMapper,UserRepository userRepository,ArticleRepository articleRepository) {
		this.commentsRepository = commentsRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.articleRepository = articleRepository;
	}
	
	public CommentEntity createComment(CreateCommentRequest req,Long userid,Long articleid) {
		var user = userRepository.findById(userid).orElseThrow(() -> new UserService.UserNotFoundException(userid));
		var article = articleRepository.findById(articleid).orElseThrow(() -> new ArticleService.ArticleNotFoundException(articleid));
		CommentEntity commentEntity = modelMapper.map(req, CommentEntity.class);
		commentEntity.setUser(user);
		commentEntity.setArticle(article);
		commentEntity.setCreatedAt(new Date());
	    return commentsRepository.save(commentEntity);
	}
	
	public Iterable<CommentEntity> findbyarticle(Long articleid){
		var article = articleRepository.findById(articleid).orElseThrow(() -> new ArticleService.ArticleNotFoundException(articleid));
		return commentsRepository.findByArticle(article);
	}
	
}
