package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.Member2VO;
import com.it.service.Member2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member2/")
public class Member2Controller {
	
	@Setter(onMethod_ = @Autowired)
	private Member2Service service;
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", service.getList()); // getList로 조회한 모든 내용을 list변수로 전달, service.getList()는 다중데이터이다.
	}

	@GetMapping("/insert")
	public void insert() { // 현재 데이터가 없는 상태인거야! 비어있는 폼으로만 나오는거야.
		// 페이지를 호출만 함
	}
	
	@PostMapping("/insert") // 포스트매핑을 통해 데이터를 가져온다.
	public String insert(Member2VO member) {
		log.info("----------멤버추가 시작---------");
		log.info(member);  // 내가 입력한 데이터'만' 불러오면 성공
		
		// 테이블에 입력
		service.insert(member); // DB로 전송할 수 있다!
		log.info("----------멤버추가 완료---------");
		
		// 리스트로 이동(return 사용)
		return "redirect:/member2/list"; // 컨트롤러를 거치지 않고 바로 이동되기 때문에 오류가 발생할 가능성이 높다. 기본원칙은 url을 이동할 때 컨트롤러를 거쳐가야해, 그래서 직접 url을 쓰지 않고 앞에 키워드를 작성해줘야 해! redirect: 키워드는 컨트롤러를 거쳐서 해당 경로로 이동하라는 의미이다.
	}
	
	@GetMapping("/view")
	public void view(Member2VO member, Model model) { // 번호를 빈 가방(보드)에 넘길꺼야
		log.info("----------읽기 전---------");
		log.info(member); // 디버깅 하는 절차야 rg?
		member = service.read(member); // ()에 글 번호를 넘기는 것 보다는 글 번호가 이미 담겨있는 board가방을 넘기는게 좋다. 
		log.info("----------읽은 후---------");
		log.info(member);
		model.addAttribute("member2", member); // 앞의 board는 변수명, 뒤의 board는 글 번호가 담긴 빈 가방(위에서 읽어낸 board)
	}

	@GetMapping("/update") // 여기는 업데이트 하기 전 데이터를 읽어서 보여주는 단계이고, 업데이트 메서드는 포스트매핑으로 따로 만들어야 한다.
	public void update(Member2VO member, Model model) { // 모델 객체 위치는 앞,뒤 상관없다.
		log.info("----------업데이트를 위한 번호---------");
		log.info(member);
		member = service.read(member); // 번호만 사용하여 조회, 아직 업데이트를 하는게 아니라 사용자가 변경하기전 데이터를 읽어서 띄우는 것 뿐이야.
		log.info("----------업데이트를 위한 데이터---------");
		log.info(member);
		model.addAttribute("member2", member);
	}
	
	@PostMapping("/update")
	public String update(Member2VO member) { // 실제 업데이트 처리 메서드를 만들거고, 보이드는 디버깅때문에 쓰는거니까 제대로 출력되면 보이드를 지우고 스트링으로 바꿔줘야해!
		log.info("----------업데이트 데이터---------");
		log.info(member);
		service.update(member); // 수정 진행
		return "redirect:/member2/view?m_id=" + member.getM_id(); // 수정 후 어디로 갈거야? => 뷰로 갈거야, 근데 번호를 같이 넘겨줘야 수정한 글을 보여주지!
	}
	
	@GetMapping("/delete")
	public String delete(Member2VO member) { 
		log.info("---------- 삭제 ---------");
		service.delete(member); // delete.jsp파일은 필요없고 이 구문에서 삭제가 처리된다.
		return "redirect:/member2/list";
	}
	
	@GetMapping("/login")
	public void login() {
		//로그인 페이지 호출
	}
	
	@PostMapping("/login")
	public String login(Member2VO member, HttpSession session) { // 로그인에서 넘어오는 데이터를 memberVO의 member가방에 담을거야. 세션객체의 세션변수를 지정해준다.
		log.info(member);
		boolean chk = service.auth(member); // 서비스에 로그인 정보를 건네준다. // 현재 서비스에 로그인이 찍히지 않으니까 여기서 해보자. // 체크변수를 불리언 타입으로 만들어서 확인해보자.
		if (chk == true) {
			member = service.read(member); // read.(member)에서 멤버에는 id와pw가 들어있다.
			session.setAttribute("m_id", member.getM_id()); // 세션변수 생성
			session.setAttribute("m_name", member.getM_name()); // 세션변수 생성
			log.info("인증성공");
			return "redirect:/";
		} else {
			log.info("인증실패");
			return "redirect:/member2/login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 이 메서드로 세션을 끊는다.(관련된 모든 세션변수가 끊어진다.)
		return "redirect:/"; // 최상위 위치 밑에 홈으로 반환 가는거야
	}
}
