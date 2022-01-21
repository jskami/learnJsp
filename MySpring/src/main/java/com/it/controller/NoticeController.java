package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.NoticeVO;
import com.it.service.NoticeService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/notice/")
public class NoticeController {

	@Setter(onMethod_ = @Autowired)
	private NoticeService service;
	
	@GetMapping("/list")
	public void list(Model model) { // list가 현재 자바 프로그램 내에서만 볼 수 있는 상태이기 때문에, 이녀석을 물고와서 웹으로 뿌려줘야 사용자도 list를 볼 수 있는데 이 역할을 해주는 녀석이 Model(모델)이다.
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/insert")
	public void insert() {
		
	}
	
	@PostMapping("/insert")
	public String insert(NoticeVO notice) {
		log.info("-------글 쓰기-------");
		log.info(notice);
		
		service.insert(notice);
		log.info("-------글 완료-------");
		
		return "redirect:/notice/list";
	}
	
	
}
