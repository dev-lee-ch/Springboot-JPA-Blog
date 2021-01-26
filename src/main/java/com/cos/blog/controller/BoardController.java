package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	@GetMapping({"", "/"})
	public String index(@AuthenticationPrincipal PrincipalDetail principal) {	// 스프링 시큐리티 세션 사용 시 @AuthenticationPrincipal 어노테이션을 사용하여 등록 된 PrincipalDetail 객체를 주입받아 사용. 
		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		return "index";
	}
}
