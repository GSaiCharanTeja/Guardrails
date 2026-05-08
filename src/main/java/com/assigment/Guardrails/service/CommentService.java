package com.assigment.Guardrails.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.assigment.Guardrails.dto.CommentRequest;
import com.assigment.Guardrails.entity.Comment;
import com.assigment.Guardrails.repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository cr;

    @Autowired
    private RedisTemplate<String, Object> rt;

    public Comment addComment(Long postId,CommentRequest req) {
        if(req.getDepthlevel() > 20) {
            throw new RuntimeException("Depth level exceeded");
        }
        if(req.isBotComment()) {
            String BCK =
                    "post:" + postId + ":bot_count";
            Long count =rt.opsForValue() .increment(BCK);
            if(count > 100) {
                rt.opsForValue().decrement(BCK);
                throw new RuntimeException(
                        "Too many bot replies");
            }
            String CDK ="cooldown:bot_"+ req.getBotId()  + ":human_"+ req.getHumanId();
            Boolean exists = rt.hasKey(CDK);
            if(Boolean.TRUE.equals(exists)) {
                throw new RuntimeException(
                        "Bot cooldown active");
            }
            rt.opsForValue().set(
                    CDK,
                    "blocked",
                    Duration.ofMinutes(10)
            );
        }
        Comment cmt = new Comment();
        cmt.setPostId(postId);
        cmt.setAuthorId(req.getAuthorId());
        cmt.setContent(req.getContent());
        cmt.setDepthLevel(req.getDepthlevel());
        cmt.setCreatedAt(LocalDateTime.now());
        Comment sc = cr.save(cmt);
        String vir ="post:" + postId+ ":virality_score";
        if(req.isBotComment()) {
            rt.opsForValue().increment(vir, 1);
        } else {
            rt.opsForValue().increment(vir, 50);
        }
        return sc;
    }
}