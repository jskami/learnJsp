package com.it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.ProductVO;
import com.it.service.ProductService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/product/")
public class ProductController {

	@Setter(onMethod_ = @Autowired)
	private ProductService service;
	
	@GetMapping("/list") // ex) http://주소:8080/board/list <-요기 (하위구조)
	public void list(Model model) { // 웹에서 처리할 이름(반드시 동일해야 할 필욘 없지만 편의상 동일하게 하는게 좋겠지?), 웹 브라우저로 전달하기 위해 만드는중 / 모델 객체..왜 뜬금 등장?
		                            // Model 객체 : VO객체를 저장해서 jsp 파일로 전송, 운반형, 내장객체, 테이블에 있는 자료를 jsp파일로 넘겨주는 역할, 현재 VO객체는 비어있는 상태이다.
									// 현재는 리스트에 줄게 없으니 이대로 두고 호출만 해보자! - 톰캣 실행!
		// list.jsp에 데이터를 전달해야 함, 오직 Model만이 데이터 전달이 가능하다!
		model.addAttribute("list", service.getList()); // getList로 조회한 모든 내용을 list변수로 전달, service.getList()는 다중데이터이다.
	}
	
	@GetMapping("/insert")
	public void insert() { // 폼을 호출하는거니까 당연히 void!
		
	}
	
	@PostMapping("/insert")
	public String insert(ProductVO product) { 
		//log.info(product);
		service.insert(product);
		return "redirect:/product/list";
	}
	
	@GetMapping("/view")
	public void view(ProductVO product, Model model) {
		product = service.read(product);
		model.addAttribute("product", product);
	}
	
	
	
	
	
}
