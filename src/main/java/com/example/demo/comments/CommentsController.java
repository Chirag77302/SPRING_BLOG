package com.example.demo.comments;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.comments.dtos.CommentResponse;
import com.example.demo.comments.dtos.CreateCommentRequest;
import com.example.demo.users.UserEntity;


@RestController
@RequestMapping("/article/{article-id}/comment")
public class CommentsController {
	
	private final CommentService commentService;
	private final ModelMapper modelMapper;
	
	public CommentsController(CommentService commentService, ModelMapper modelMapper) {
		this.commentService = commentService;
		this.modelMapper = modelMapper;
	}

	@PostMapping("")
	ResponseEntity<CommentResponse> createcomment(@AuthenticationPrincipal UserEntity user, @PathVariable("article-id") String articleid, @RequestBody CreateCommentRequest comm) {
		CommentEntity comment = commentService.createComment(comm, user.getId(), Long.parseLong(articleid));
		CommentResponse response = modelMapper.map(comment, CommentResponse.class);
		return ResponseEntity.ok(response);
	}
	
	
	@GetMapping("")
	Iterable<CommentEntity> commentsonanarticle(@PathVariable("article-id") String articleid){
		Iterable<CommentEntity> comments = commentService.findbyarticle(Long.parseLong(articleid));
		return comments;
	}
	
}
