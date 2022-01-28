package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.MemberVO;
import com.it.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/")
public class MemberController {

	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
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
	public String insert(MemberVO member) {
		log.info("----------글쓰기 시작---------");
		log.info(member);  // 내가 입력한 데이터'만' 불러오면 성공
		
		// 테이블에 입력
		service.insert(member); // DB로 전송할 수 있다!
		log.info("----------글쓰기 완료---------");
		
		// 리스트로 이동(return 사용)
		return "redirect:/member/list"; // 컨트롤러를 거치지 않고 바로 이동되기 때문에 오류가 발생할 가능성이 높다. 기본원칙은 url을 이동할 때 컨트롤러를 거쳐가야해, 그래서 직접 url을 쓰지 않고 앞에 키워드를 작성해줘야 해! redirect: 키워드는 컨트롤러를 거쳐서 해당 경로로 이동하라는 의미이다.
	}
	
	@GetMapping("/view")
	public void view(MemberVO member, Model model) { // 번호를 빈 가방(보드)에 넘길꺼야
		log.info("----------읽기 전---------");
		log.info(member); // 디버깅 하는 절차야 rg?
		member = service.read(member); // ()에 글 번호를 넘기는 것 보다는 글 번호가 이미 담겨있는 board가방을 넘기는게 좋다. 
		log.info("----------읽은 후---------");
		log.info(member);
		model.addAttribute("member", member); // 앞의 board는 변수명, 뒤의 board는 글 번호가 담긴 빈 가방(위에서 읽어낸 board)
	}

	@GetMapping("/update") // 여기는 업데이트 하기 전 데이터를 읽어서 보여주는 단계이고, 업데이트 메서드는 포스트매핑으로 따로 만들어야 한다.
	public void update(MemberVO member, Model model) { // 모델 객체 위치는 앞,뒤 상관없다.
		log.info("----------업데이트를 위한 번호---------");
		log.info(member);
		member = service.read(member); // 번호만 사용하여 조회, 아직 업데이트를 하는게 아니라 사용자가 변경하기전 데이터를 읽어서 띄우는 것 뿐이야.
		log.info("----------업데이트를 위한 데이터---------");
		log.info(member);
		model.addAttribute("member", member);
	}
	
	@PostMapping("/update")
	public String update(MemberVO member) { // 실제 업데이트 처리 메서드를 만들거고, 보이드는 디버깅때문에 쓰는거니까 제대로 출력되면 보이드를 지우고 스트링으로 바꿔줘야해!
		log.info("----------업데이트 데이터---------");
		log.info(member);
		service.update(member); // 수정 진행
		return "redirect:/member/view?m_id=" + member.getM_id(); // 수정 후 어디로 갈거야? => 뷰로 갈거야, 근데 번호를 같이 넘겨줘야 수정한 글을 보여주지!
	}
	
	@GetMapping("/delete")
	public String delete(MemberVO member) { 
		log.info("---------- 삭제 ---------");
		service.delete(member); // delete.jsp파일은 필요없고 이 구문에서 삭제가 처리된다.
		return "redirect:/member/list";
	}
	
}
