package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO 역할
// 자동으로 bean으로 등록됨.
// @Repository //생략가능
public interface UserRepository extends JpaRepository<User, Integer> {

	// 방법1. 조건쿼리
	// JPA Naming 전략(쿼리) => SELECT * FROM user WHERE username = ? AND password = ?;
	User findByUsernameAndPassword(String username, String password);
	
	// 방법2. 조건쿼리
	@Query(value="SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
	User login(String username, String password);
}
