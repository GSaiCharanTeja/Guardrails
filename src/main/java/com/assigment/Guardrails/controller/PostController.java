package com.assigment.Guardrails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assigment.Guardrails.dto.PostRequest;
import com.assigment.Guardrails.entity.Post;
import com.assigment.Guardrails.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService ps;
	
	@PostMapping
	public ResponseEntity<Post> createPost(@RequestBody PostRequest req){
		return ResponseEntity.ok(ps.createPost(req));
	}
	
	
	@PostMapping("/{postId}/like")
	public ResponseEntity<String> likePost(@PathVariable Long postId){
		return ResponseEntity.ok(ps.lp(postId));
	}
}
