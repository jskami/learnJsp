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
		model.addAttribute("list", service.getList()); // getList로 조회한 모든 내용을 list변수로 전달, service.getList()는 다중데이터이다.
	}

	@GetMapping("/insert")
	public void insert() { // 현재 데이터가 없는 상태인거야! 비어있는 폼으로만 나오는거야.
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert") // 포스트매핑을 통해 데이터를 가져온다.
	public String insert(BoardVO board) {
		log.info("----------글쓰기 시작---------");
		log.info(board);  // 내가 입력한 데이터'만' 불러오면 성공
		
		// 테이블에 입력
		service.insert(board); // DB로 전송할 수 있다!
		log.info("----------글쓰기 완료---------");
		
		// 리스트로 이동(return 사용)
		return "redirect:/board/list"; // 컨트롤러를 거치지 않고 바로 이동되기 때문에 오류가 발생할 가능성이 높다. 기본원칙은 url을 이동할 때 컨트롤러를 거쳐가야해, 그래서 직접 url을 쓰지 않고 앞에 키워드를 작성해줘야 해! redirect: 키워드는 컨트롤러를 거쳐서 해당 경로로 이동하라는 의미이다.
	}
	
	@GetMapping("/view")
	public void view(BoardVO board, Model model) { // 번호를 빈 가방(보드)에 넘길꺼야
		log.info("----------읽기 전---------");
		log.info(board); // 디버깅 하는 절차야 rg?
		board = service.read(board); // ()에 글 번호를 넘기는 것 보다는 글 번호가 이미 담겨있는 board가방을 넘기는게 좋다. 
		log.info("----------읽은 후---------");
		log.info(board);
		model.addAttribute("board", board); // 앞의 board는 변수명, 뒤의 board는 글 번호가 담긴 빈 가방(위에서 읽어낸 board)
	}

	@GetMapping("/update") // 여기는 업데이트 하기 전 데이터를 읽어서 보여주는 단계이고, 업데이트 메서드는 포스트매핑으로 따로 만들어야 한다.
	public void update(BoardVO board, Model model) { // 모델 객체 위치는 앞,뒤 상관없다.
		log.info("----------업데이트를 위한 번호---------");
		log.info(board);
		board = service.read(board); // 번호만 사용하여 조회, 아직 업데이트를 하는게 아니라 사용자가 변경하기전 데이터를 읽어서 띄우는 것 뿐이야.
		log.info("----------업데이트를 위한 데이터---------");
		log.info(board);
		model.addAttribute("board", board);
	}
	
	@PostMapping("/update")
	public String update(BoardVO board) { // 실제 업데이트 처리 메서드를 만들거고, 보이드는 디버깅때문에 쓰는거니까 제대로 출력되면 보이드를 지우고 스트링으로 바꿔줘야해!
		log.info("----------업데이트 데이터---------");
		log.info(board);
		service.update(board); // 수정 진행
		return "redirect:/board/view?b_num=" + board.getB_num(); // 수정 후 어디로 갈거야? => 뷰로 갈거야, 근데 번호를 같이 넘겨줘야 수정한 글을 보여주지!
	}
	
	@GetMapping("/delete")
	public String delete(BoardVO board) { 
		log.info("---------- 삭제 ---------");
		service.delete(board); // delete.jsp파일은 필요없고 이 구문에서 삭제가 처리된다.
		return "redirect:/board/list";
	}
	
}
