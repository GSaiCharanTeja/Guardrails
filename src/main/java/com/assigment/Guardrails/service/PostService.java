package com.assigment.Guardrails.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.assigment.Guardrails.dto.PostRequest;
import com.assigment.Guardrails.entity.Post;
import com.assigment.Guardrails.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository pr;
	
	public Post createPost(PostRequest req) {
		Post post=new Post();
		
		post.setAuthorId(req.getAuthorId());
		post.setContent(req.getContent());
		post.setCreatedAt(LocalDateTime.now());
		
		return pr.save(post);
	}
	
	@Autowired
	private RedisTemplate<String,Object> rt;
	
	public String lp(Long postId) {
		String vir="post:"+postId+":virality_score";
		rt.opsForValue().increment(vir,20);
		
		return "Post Liked Successfully";
	}

}
