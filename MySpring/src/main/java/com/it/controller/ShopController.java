package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;
import com.it.domain.OrdermainVO;
import com.it.domain.OrdermemberDTO;
import com.it.service.CartService;
import com.it.service.MemberService;
import com.it.service.OrderService;
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
	
	@Setter(onMethod_ = @Autowired)
	private OrderService orderservice;
	
	
	
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
			return "redirect:/shop/cartinfo"; // redirect: 의 의미는 컨트롤러로 다시 돌아오라는 호출의 의미이다.(컨트롤러를 거쳐서 진행)
		} else { 
			return "redirect:/member/login"; // 로그인 상태가 아니라면 항상 이렇게 돌려주면 된다.
		}
	}
	
	@GetMapping("/cartinfo")
	public String cartinfo(HttpSession session, Model model) {
		// 로그인 상태 확인 (제어구조(세션변수를 확인)를 먼저 만들어보자)
		String m_id = (String)session.getAttribute("m_id");
		String m_name = (String)session.getAttribute("m_name");
		if (m_id != null) {
		// 세션아이디를 이용해서 cm_code를 조회해야 함 / 로그인이 되어있다는 가정 하에 진행 
			CartmainVO cartmain = new CartmainVO();
			cartmain.setM_id(m_id);
			cartmain = cartservice.readMainid(cartmain);
			if (cartmain != null) { // 로그인은 되었는데 cartmain(장바구니)이 있을수도 있고 없을 수도 있으니 확인해야 한다.
				// int cm_code = cartmain.getCm_code(); // 로그인된 사용자의 아이디를 사용하는 cartmain의 cm_code / 필요없어졌다.
				// cartservice.getListCart(cartmain).forEach(cartsub -> log.info(cartsub)); // 디버깅용으로 쓴거니까 주석
				
				model.addAttribute("list", cartservice.getListCartDetail(cartmain));
				
				CartmemberDTO carttotal = cartservice.getCartTotal(cartmain);// 총 금액'만'들어있다.
				carttotal.setCm_code(cartmain.getCm_code());
				cartmain.setM_id(m_id);
				cartmain.setM_id(m_name);
				model.addAttribute("carttotal", carttotal); // 총 금액 데이터를 담을 그릇은 만든상태인데, 정작 데이터가 없다. 그래서 나머지 정보를 넘겨줄 방법을 찾아보자
				model.addAttribute("cm_code", cartmain.getCm_code()); // cm_code를 전달하기 위해(cartinfo의 삭제를 위해)
				log.info("장바구니 내용 있음"); // cm_code를 통해 cartSub를 조회할거야
			} else {
				log.info("장바구니 내용 없음");
			}
			// cm_code를 이용해서 getListCart를 조회해서 리스트 출력
			log.info("로그인 상태");
			return "/shop/cartinfo"; // 현재 페이지로(자기 자신) 이동(반드시 작성) 여기는 redirect를 쓰면 안된다. 이미 위에서 진행 되었기 때문
		} else {
			log.info("로그아웃 상태");
			return "/member/login"; // 컨트롤러의 메서드를 호출 한 뒤 jsp로 이동, redirect 생략시 jsp페이지로 바로 이동(컨트롤러 안거치고), 컨트롤러 통과 여부를 잘 모르겠으면 redirect를 그냥 쓰는게 낫다.
		}	// 리다이렉트를 하면 다시 또 계산을 하니까 문제가 발생하겠지? 그래서 로그인 하고 페이지 이동할 때 리다이렉트(두 번 일하지말라고)안쓰는거야
		
	}
	
	@PostMapping("/cartupdate")
	public String cartupdate(CartsubVO cartsub) { // 현재 위치에서 머무르지 않고 처리한 후 cartinfo로 넘어가면 돼
		cartservice.updateSub(cartsub); // 상세코드와 수량이 넘어가도록 설정
		return "redirect:/shop/cartinfo";
	}
	
	@GetMapping("/cartdelete")
	public String cartdelete(CartsubVO cartsub) { // 삭제 역시 현재 위치에 머무르지 않고 처리하는데.. 일단 테스트니까 보이드로 확인해보자
		cartservice.deleteSub(cartsub);
		return "redirect:/shop/cartinfo";
	}
	
	@GetMapping("/cartdeleteall")
	public String cartdeleteall(CartmainVO cartmain) {
		cartservice.deleteSuball(cartmain);
		return "/shop/cartinfo";
	}
	
	@GetMapping("/orderinfo")
	public String orderinfo(HttpSession session, CartmainVO cartmain, Model model) {
		String m_id = (String)session.getAttribute("m_id");
		String m_name = (String)session.getAttribute("m_name");
		if (m_id != null) {
			
		cartmain.setM_id(m_id);
		OrdermainVO ordermain = orderservice.orderproc(cartmain); // om_code 획득, orderproc를 호출하여 cm_code 값을 넘김
		
		model.addAttribute("list", orderservice.getListOrderDetail(ordermain));
		
		OrdermemberDTO ordertotal = orderservice.getOrderTotal(ordermain);
		ordertotal.setOm_code(ordermain.getOm_code());
		ordermain.setM_id(m_id);
		ordermain.setM_id(m_name);
		
		model.addAttribute("ordertotal", ordertotal); // 총 금액 데이터를 담을 그릇은 만든상태인데, 정작 데이터가 없다. 그래서 나머지 정보를 넘겨줄 방법을 찾아보자
		model.addAttribute("om_code", ordermain.getOm_code());
		
		log.info("로그인 상태");
		return "/shop/orderinfo";
	} else {
		log.info("로그아웃 상태");
		return "/member/login";
	}
	
		
		
		
	
	
	
	}
}
