package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.BoardVO;
import com.it.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/") // URL 설정, ex) http://주소:8080/board/~~  (상위구조) 최상위에서 폴더 하나를 더 만드는거라 보면 됨 , 해당 폴더는 외부에서 직접 컨트롤 할 수 없음!
public class BoardController {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@GetMapping("/list") // ex) http://주소:8080/board/list <-요기 (하위구조)
	public void list(Model model) { // 웹에서 처리할 이름(반드시 동일해야 할 필욘 없지만 편의상 동일하게 하는게 좋겠지?), 웹 브라우저로 전달하기 위해 만드는중 / 모델 객체..왜 뜬금 등장?
		                            // Model 객체 : VO객체를 저장해서 jsp 파일로 전송, 운반형, 내장객체, 테이블에 있는 자료를 jsp파일로 넘겨주는 역할, 현재 VO객체는 비어있는 상태이다.
									// 현재는 리스트에 줄게 없으니 이대로 두고 호출만 해보자! - 톰캣 실행!
		// list.jsp에 데이터를 전달해야 함, 오직 Model만이 데이터 전달이 가능하다!
		model.addAttribute("list", service.getList()); // getList로 조회한 모든 내용을 list변수로 전달
	}

	@GetMapping("/insert")
	public void insert() { // 현재 데이터가 없는 상태인거야! 비어있는 폼으로만 나오는거야.
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert") // 포스트매핑을 통해 데이터를 가져온다.
	public void insert(BoardVO board) {
		service.insert(board); // DB로 전송할 수 있다!
		log.info(board);  // 내가 입력한 데이터'만' 불러오면 성공
	}
	
	
	
	
}
