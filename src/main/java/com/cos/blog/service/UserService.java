package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
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
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@Transactional
	public int save(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encode.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		return 1;
	}

	@Transactional
	public void updateUser(User requestUser) {
		User user = userRepository.findById(requestUser.getId())
			.orElseThrow(()->{
				return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
			}); // 영속화 완료
		
		String rawPassword = requestUser.getPassword();
		String encPassword = encode.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setEmail(requestUser.getEmail());
		
	}

//	기존 세션 로그인 방식
//	@Transactional(readOnly = true)	// Select 할 때 트랜젝션 시작, 서비스 종료 시에 트랜젝션 종료 (정합성 유지)
//	public User login(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
