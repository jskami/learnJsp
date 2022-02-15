package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.CartmainVO;
import com.it.domain.CartsubVO;
import com.it.service.CartService;
import com.it.service.MemberService;
import com.it.service.ProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/shop/")
public class ShopController {

	@Setter(onMethod_ = @Autowired) // 의존성 주입!
	private ProductService productservice;
	
	@Setter(onMethod_ = @Autowired)
	private MemberService memberservice;
	
	@Setter(onMethod_ = @Autowired)
	private CartService cartservice;
	
	
	
	@GetMapping("/list") // ex) http://주소:8080/board/list <-요기 (하위구조)
	public void list(Model model) { // 웹에서 처리할 이름(반드시 동일해야 할 필욘 없지만 편의상 동일하게 하는게 좋겠지?), 웹 브라우저로 전달하기 위해 만드는중 / 모델 객체..왜 뜬금 등장?
		                            // Model 객체 : VO객체를 저장해서 jsp 파일로 전송, 운반형, 내장객체, 테이블에 있는 자료를 jsp파일로 넘겨주는 역할, 현재 VO객체는 비어있는 상태이다.
									// 현재는 리스트에 줄게 없으니 이대로 두고 호출만 해보자! - 톰캣 실행!
		// list.jsp에 데이터를 전달해야 함, 오직 Model만이 데이터 전달이 가능하다!
		model.addAttribute("list", productservice.getList()); // getList로 조회한 모든 내용을 list변수로 전달, service.getList()는 다중데이터이다.
	}
	
	@PostMapping("/cart")
	public String cart(HttpSession session, CartsubVO cartsub) { // 상품과 개수가 넘어오게 된다. + 세션 정보를 추가한다.
		log.info("=======장바구니 테스트=======");
		log.info(cartsub);
		String m_id = (String)session.getAttribute("m_id"); // 지금 과정은 비회원이 cartmain까지 못가도록 cartsub에서 세션체크를 하기 위함이다.
		if (m_id != null) { // 로그인이 되어있을 경우 장바구니 처리 진행
			CartmainVO cartmain = new CartmainVO(); 
			cartmain.setM_id(m_id); // VO에 사용자의 세션정보를 저장
			cartservice.cartinsert(cartmain, cartsub); // 서비스계층 호출
			return "redirect:/shop/cartinfo"; // redirect: 의 의미는 컨트롤러로 다시 돌아오라는 호출의 의미이다.
		} else { 
			return "redirect:/member/login";
		}
	}
	
	@GetMapping("/cartinfo")
	public void cartinfo() {
		// 세션아이디를 이용해서 cm_code를 조회해야 함
		// cm_code를 이용해서 getListCart를 조회해서 리스트 출력
		// cartinfo.jsp에 넘긴다.
	}
	
	
	
}
