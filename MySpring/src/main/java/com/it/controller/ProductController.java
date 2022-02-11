package com.it.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
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
		model.addAttribute("product", product); // ★뷰는 단일 데이터만 불러오는게 아니기 때문에 프로덕트 가방에 있는 전체 데이터를 불러와야 하니까 product.getP_code처럼 하나의 데이터만 불러오는게 아니다.
	}
	
	@GetMapping("/imgupload")
	public void imgupload(ProductVO product, Model model) {
		log.info(product);
		model.addAttribute("p_code", product.getP_code()); //★ 넘기는건 상품 코드이다. 어디서? 프로덕트 가방에서! 그러나, 상세정보에서 해당 상품(단일 데이터)만 불러오려면 가방전체(product)가 아닌, 가방 내에서 해당하는 데이터(getP_code)로 불러오는 것이다.
	}
	
	@PostMapping("/imgupload")
	public void imgupload(HttpServletRequest request) {
		DiskFileUpload upload = new DiskFileUpload(); // 파일 전송 컴포넌트 객체 생성
		try {
			List items = upload.parseRequest(request); // 웹브라우저 전송 객체 생성해서 업로드 컴포넌트에 전달
			Iterator params = items.iterator(); // 반복자 생성, params변수로 반복생성 하도록 만든 상태
			String imgpath = "C:\\myWorkSpace\\learnJsp\\MySpring\\src\\main\\webapp\\resources\\product"; // 저장 위치
			String p_code = "";
			//log.info(items.size());
			while (params.hasNext()) { // form 객체가 있을 경우
				FileItem item = (FileItem)params.next(); // 폼 형식 객체를 변수에 저장
				if (item.isFormField()) { // 파일 형식이 아니라면 / 폼 내에 필드 타입 형식이 무엇이냐를 묻는 것
					p_code = item.getString(); // 파일보다 먼저 반환 된다.
				} else { // 바이너리 파일이라면
					File imgfile = new File(imgpath + "/" + p_code + ".jpg"); // 파일객체 생성
					item.write(imgfile); // 해당 경로에 파일 저장, 여기가 최종 작업
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	
	
}
