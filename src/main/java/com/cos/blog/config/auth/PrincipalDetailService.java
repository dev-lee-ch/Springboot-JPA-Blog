package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// UserDetailsService를 구현하지 않으면, 시프링시큐리티 세션에서 프로젝트에서 만든 User 객체를 사용 할 수 없음.
@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로채는데,
	// password 부분 처리는 알아서 함.
	// username이 DB에 있는지만 확인해주면 됨.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
			.orElseThrow(() -> {
				return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
			});
		return new PrincipalDetail(principal);	// 이때, 시큐리티의 세션에 유저정보가 저장됨.
	}

}
