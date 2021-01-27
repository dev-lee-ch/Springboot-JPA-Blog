package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping({"", "/"})
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {	// @AuthenticationPrincipal PrincipalDetail principal 스프링 시큐리티 세션 사용 시 @AuthenticationPrincipal 어노테이션을 사용하여 등록 된 PrincipalDetail 객체를 주입받아 사용. 
//		System.out.println("로그인 사용자 아이디 : " + principal.getUsername());
		
		Page<Board> pagingBoard = boardService.boardList(pageable);
		model.addAttribute("boards", pagingBoard);
		
		return "index";	// viewResolver 작동!!
	}
	
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.detail(id));
		
		return "board/detail";
	}
}
