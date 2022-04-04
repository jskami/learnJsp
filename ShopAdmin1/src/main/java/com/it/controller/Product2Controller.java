package com.it.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.PageDTO;
import com.it.domain.PageViewDTO;
import com.it.domain.Product2VO;
import com.it.service.Product2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/product2/")
public class Product2Controller {

	@Setter(onMethod_ = @Autowired)
	private Product2Service service;
	
	@GetMapping("/list") 
	public String list(Model model, PageDTO page, HttpSession session) {                            
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		model.addAttribute("list", service.getList(page)); // getList로 조회한 모든 내용을 list변수로 전달, service.getList()는 다중데이터이다.
		int total = service.getTotalCount();
		PageViewDTO pageview = new PageViewDTO(page, total);
		model.addAttribute("pageview", pageview);
		}
		return "/product2/list";
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session) { // 폼을 호출하는거니까 당연히 void!
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			return "/product2/insert";
		}
	}
	
	@PostMapping("/insert")
	public String insert(Product2VO product) { 
		//log.info(product);
		service.insert(product);
		return "redirect:/product2/list";
	}
	
	@GetMapping("/view")
	public String view(Product2VO product, Model model, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "/admin/login";
		} else {
		product = service.read(product);
		model.addAttribute("product", product); // ★뷰는 단일 데이터만 불러오는게 아니기 때문에 프로덕트 가방에 있는 전체 데이터를 불러와야 하니까 product.getP_code처럼 하나의 데이터만 불러오는게 아니다.
		model.addAttribute("page", page);
		} return "/product2/view";
	}	
	
	@GetMapping("/imgupload")
	public void imgupload(Product2VO product, Model model) {
		log.info("해당 상품코드:" + product.getP_code());
		model.addAttribute("p_code", product.getP_code()); //★ 넘기는건 상품 코드이다. 어디서? 프로덕트 가방에서! 그러나, 상세정보에서 해당 상품(단일 데이터)만 불러오려면 가방전체(product)가 아닌, 가방 내에서 해당하는 데이터(getP_code)로 불러오는 것이다.
	}
	
	@PostMapping("/imgupload")
	public void imgupload(HttpServletRequest request) {
		DiskFileUpload upload = new DiskFileUpload(); // 파일 전송 컴포넌트 객체 생성
		try {
			List items = upload.parseRequest(request); // 웹브라우저 전송 객체 생성해서 업로드 컴포넌트에 전달
			Iterator params = items.iterator(); // 반복자 생성, params변수로 반복생성 하도록 만든 상태
			String imgpath = "C:\\myWorkSpace\\learnJsp\\ShopAdmin1\\src\\main\\webapp\\resources\\product"; // 저장 위치
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
	
	@GetMapping("/update")
	public String update(Product2VO product, Model model, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("-------------업데이트를 위한 번호 ------------------");
		log.info(product);
		product = service.read(product);  //번호만 사용하여 조회
		log.info("-------------업데이트를 위한 데이터-------------------");
		log.info(product);
		model.addAttribute("product", product);
		model.addAttribute("page", page);
		} return "/product2/update";
	}
	
	@PostMapping("/update")
	public String update(Product2VO product, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("-------- 업데이트 데이터 ---------------");
		log.info(product);
		service.update(product); //업데이트
		//return "redirect:/product2/view?p_code=" + product.getP_code() + "&pageNum=" + page.getPageNum();
		} return "redirect:/product2/view?p_code=" + product.getP_code() + "&pageNum=" +page.getPageNum();
	}
	
	@GetMapping("/delete")
	public String delete(Product2VO product) { 
		log.info("---------- 삭제 ---------");
		service.delete(product); // delete.jsp파일은 필요없고 이 구문에서 삭제가 처리된다.
		log.info(product);
		return "redirect:/product2/list";
	}
	
}
