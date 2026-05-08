package com.assigment.Guardrails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.Guardrails.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
