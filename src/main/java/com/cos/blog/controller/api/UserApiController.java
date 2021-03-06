package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {	// username, password, email
		System.out.println("User Api Controller : save 호출됨");
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), userService.save(user)) ;
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		userService.updateUser(user);
		// DB 처리는 완료되었지만,
		// 세션 업데이트 처리는 되어있지 않음 -> 직접 세션값을 변경해줘야함.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	//	기존 세션 로그인 방식
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("User Api Controller : login 호출됨");
//		
//		User principal = userService.login(user);	// pricipal (접근주체)
//		System.out.println("principal : " + principal);
//		if (principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), principal != null ? 1 : -1) ;
//	}
	
}
