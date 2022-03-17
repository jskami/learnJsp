package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.BoardVO;
import com.it.domain.PageDTO;
import com.it.domain.PageViewDTO;
import com.it.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/")
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model, PageDTO page/*, @RequestParam("user") String user,
			@RequestParam("age") int age*/) {  //Model 객체는 VO객체를 저장해서 jsp 파일로 전송, 현재는 비어있음
											//list.jsp 에 데이터를 전달해야 함
		model.addAttribute("list", service.getList(page));  //getList로 조회한 모든 내용을 list변수로 전달
		int total = service.getTotalCount();	//전체 레코드 갯수
		PageViewDTO pageview = new PageViewDTO(page, total);
		log.info(page);
		log.info(pageview);
		model.addAttribute("pageview", pageview);
		log.info("요기----------------------------------------");
		//log.info(user);
		//log.info(age + 1);
		//model.addAttribute("user", user);
		//model.addAttribute("age", age);
	}
	
	@GetMapping("/insert")
	public void insert() {
		//페이지를 호출만 함
	}
	
	@PostMapping("/insert")
	public String insert(BoardVO board) {
		log.info("-----------글쓰기 시작--------------------");
		log.info(board);
		//테이블에 입력
		service.insert(board);
		//리스트로 이동(return 사용)
		log.info("-----------글쓰기 완료--------------------");
		
		return "redirect:/board/list";  //controller 를 통해서 이동
	}
	
	@GetMapping("/view")
	public void view(BoardVO board, Model model, PageDTO page) {
		log.info("---------------읽기 전--------------------");
		log.info(board);
		board = service.read(board);
		log.info("----------------읽은 후-------------------");
		log.info(board);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
	}
	
	@GetMapping("/update")
	public void update(BoardVO board, Model model, PageDTO page) {
		log.info("-------------업데이트를 위한 번호 ------------------");
		log.info(board);
		board = service.read(board);  //번호만 사용하여 조회
		log.info("-------------업데이트를 위한 데이터-------------------");
		log.info(board);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
	}
	
	@PostMapping("/update")
	public String update(BoardVO board, PageDTO page) {
		log.info("-------- 업데이트 데이터 ---------------");
		log.info(board);
		service.update(board); //업데이트
		return "redirect:/board/view?b_num=" + board.getB_num() + "&pageNum=" + page.getPageNum();
	}
	
	@GetMapping("/delete")
	public String delete(BoardVO board) {
		log.info("------------- 삭제 -----------------");
		service.delete(board);
		return "redirect:/board/list";
	}
	
	

}
