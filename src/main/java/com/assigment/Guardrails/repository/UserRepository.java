package com.assigment.Guardrails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assigment.Guardrails.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
}
