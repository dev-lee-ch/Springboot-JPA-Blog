package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
/* 서비스
 * 1. 트랜젝션 관리
 * 2. 서비스의 의미
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Transactional
	public int save(User user) {
		try {
			userRepository.save(user);
			return 1;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("UserService : 회원가입 : "+ e.getMessage());
		}
		
		return -1;
	}

	@Transactional(readOnly = true)	// Select 할 때 트랜젝션 시작, 서비스 종료 시에 트랜젝션 종료 (정합성 유지)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
