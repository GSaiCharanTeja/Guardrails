package com.assigment.Guardrails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.Guardrails.entity.Post;
@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
