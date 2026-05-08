package com.assigment.Guardrails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.Guardrails.dto.CommentRequest;
import com.assigment.Guardrails.entity.Comment;
import com.assigment.Guardrails.service.CommentService;

@RestController
@RequestMapping("/api/posts")
public class CommentController {
	@Autowired
	private CommentService cs;
	
	@PostMapping("/{postId}/comments")
	public ResponseEntity<Comment>addComment(@PathVariable Long postId,@RequestBody CommentRequest req){
		return ResponseEntity.ok(cs.addComment(postId,req));
	}
}
