package com.it.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.Board2VO;
import com.it.domain.PageDTO;
import com.it.domain.PageViewDTO;
import com.it.service.Board2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board2/")
public class Board2Controller {

	@Setter(onMethod_ = @Autowired)
	private Board2Service service;
	
	@GetMapping("/list") // ex) http://주소:8080/board/list <-요기 (하위구조)
	public String list(Model model, PageDTO page, HttpSession session ) {
									/*, @RequestParam("user") String user, @RequestParam("age") int age*/ // 웹에서 처리할 이름(반드시 동일해야 할 필욘 없지만 편의상 동일하게 하는게 좋겠지?), 웹 브라우저로 전달하기 위해 만드는중 / 모델 객체..왜 뜬금 등장? / @RequestParam(변수명 지정) 타입 변수명 / 괄호 안의 파란색 유저는 웹 브라우저에서 사용하는 변수명이고 타입 뒤에 갈색 유저는 클래스 내에서 쓰는 변수명이다.
		                            // Model 객체 : VO객체를 저장해서 jsp 파일로 전송, 운반형, 내장객체, 테이블에 있는 자료를 jsp파일로 넘겨주는 역할, 현재 VO객체는 비어있는 상태이다.
									// 현재는 리스트에 줄게 없으니 이대로 두고 호출만 해보자! - 톰캣 실행!
									// list.jsp에 데이터를 전달해야 함, 오직 Model만이 데이터 전달이 가능하다!
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		model.addAttribute("list", service.getList(page)); // getList로 조회한 모든 내용을 list변수로 전달, service.getList()는 다중데이터이다.
		int total = service.getTotalCount();  // 전체 레코드 개수 호출
		PageViewDTO pageview = new PageViewDTO(page, total);
		model.addAttribute("pageview", pageview);
	} return "/board2/list";
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session) { // 현재 데이터가 없는 상태인거야! 비어있는 폼으로만 나오는거야.
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
			return "/board2/insert";
		}
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		DiskFileUpload upload = new DiskFileUpload(); // 파일 전송 컴포넌트 객체 생성
		try {
			List items = upload.parseRequest(request); // 웹브라우저 전송 객체 생성해서 업로드 컴포넌트에 전달
			log.info("=========구분선========");
			log.info(request);
			log.info(items);
			
			Iterator params = items.iterator(); // 반복자 생성, params변수로 반복생성 하도록 만든 상태
			log.info("=========구분선========");
			log.info(params);
			String filepath = "C:\\myWorkSpace\\learnJsp\\pds"; // 저장 위치(물리적 경로)
			//log.info(items.size());
			Board2VO board = new Board2VO(); // 객체생성
			while (params.hasNext()) { // form 객체가 있을 경우
				FileItem item = (FileItem)params.next(); // 폼 형식 객체를 변수에 저장, 필드 순서로 하나씩 추출
				if (item.isFormField()) { // 파일 형식이 아니라면 / 폼 내에 필드 타입 형식이 무엇이냐를 묻는 것
					// p_code = item.getString(); // 파일보다 먼저 반환 된다.
					String fieldname = item.getFieldName(); // jsp파일에 있는 name들을 말한다. 이제 값도 설정해보자
					String fieldvalue = item.getString("utf-8");
					log.info("=========구분선========");
					log.info(fieldname + ":" + fieldvalue);
					if (fieldname.equals("b_subject")) { // 문자열 비교할거니까 이퀄스를 사용해야해!
						board.setB_subject(fieldvalue);
					} else if (fieldname.equals("b_name")) {
						board.setB_name(fieldvalue);
					} else if (fieldname.equals("b_contents")) {
						board.setB_contents(fieldvalue);
					}
				} else { // 바이너리 파일이라면
					String fname = item.getName();
					log.info("=========구분선========");
					log.info(fname);
					if (fname != "") {
						board.setB_file(fname);
						File file = new File(filepath + "/" + fname); // 파일객체 생성
						item.write(file); // 해당 경로에 파일 저장, 여기가 최종 작업
					}
				}
			} log.info(board);
			service.insert(board);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/board2/list";
		}
	}
	
	@GetMapping("/view")
	public String view(Board2VO board, Model model, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("---------------읽기 전--------------------");
		log.info(board);
		board = service.read(board);
		log.info("----------------읽은 후-------------------");
		log.info(board);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		} return "/board2/view";
	}
	
	@GetMapping("/update")
	public String update(Board2VO board, Model model, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("-------------업데이트를 위한 번호 ------------------");
		log.info(board);
		board = service.read(board);  //번호만 사용하여 조회
		log.info("-------------업데이트를 위한 데이터-------------------");
		log.info(board);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		} return "/board2/update";
	}
	
	@PostMapping("/update")
	public String update(Board2VO board, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("-------- 업데이트 데이터 ---------------");
		log.info(board);
		service.update(board); //업데이트
		} return "redirect:/board2/view?b_num=" + board.getB_num() + "&pageNum=" + page.getPageNum();
	}
	
	@GetMapping("/delete")
	public String delete(Board2VO board, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("------------- 삭제 -----------------");
		service.delete(board);
		} return "redirect:/board2/list";
	}
	
	
	@GetMapping("/download")
	public void download(Board2VO board, HttpServletResponse response) {
		board = service.read(board); // 다운로드시 파일 명으로 나타나는게 아닌 번호로 나타나게 해보았다. b_num을 이용해서 해당 레코드 정보 반환. 예외처리어도 아니어도 관계 없다.
		try {
			String filepath = "C:\\myWorkSpace\\learnJsp\\pds\\" + board.getB_file(); // filepath가 완전한 파일이름을 갖도록 설정했다.
			File file = new File(filepath);
			
			// String newName = new String(file.getName().getBytes("UTF-8"), "IOS-8859-1"); // 파일명이 한글일 경우 다운로드시 한글깨짐을 방지하기 위한 코드이다.
			response.setHeader("Content-Disposition", "attachment;filename=" + file.getName()); // 내 주석 : 파일 전송용으로만 쓰인다. 웹페이지 전송이 아닌 파일 다운로드로 인식하라는 요청문이다. 그러면 웹브라우저가 웹페이지가 아닌 다운로드만 하려는구나! 라고 인식해서 다운로드 처리를 진행하고 후반부에 어떤 파일이 오는지 명시해줘야 한다. 사전에 파일 객체의 이름이 있어야한다.
																								// 선생님 주석 : 웹 페이지가 아닌 파일객체를 클라이언트에 전송한다는 지시어, 기본값은 text/html이다. 클라이언트 웹브라우저에 파일다운로드가 처리된다.
			log.info("=========구분선========");
			log.info(file.getName());
			FileInputStream fis = new FileInputStream(filepath); 
			OutputStream out = response.getOutputStream(); // 여기부터는 인터넷 개념으로 접근.. out객체는 response로 생성한다.
			
			int read = 0; // 1024 단위로 읽은 바이트 수 , 읽는 양 만큼 read에 가져오겠다는 의미 ex) 1024kb씩 담아 올 수 있는데, 가져오려는 파일이 1050kb라면 1024한 번 가져오고 나머지 26만 가져온다. 어쨌든 읽은 바이트수는 1050이고 읽어낸 값을 read에 담는다.
			byte[] buffer = new byte[1024]; // 임으로 조정 가능
			while ((read = fis.read(buffer)) != -1) { // fis.read(buffer)는 버퍼를 읽어내고, 못 읽어내면 -1을 반환한다.(= 같지 않다는 루트를 돌아야한다.) , 여기서의 read는 얼마나 읽어냈는가를 알기 위한 기록일 뿐 읽어낸 실체는 알 수 없다. 여기서 file은 몇 바이트인지도 모르고 얼마나 있는지도 몰라서 반복구문으로 뽑아내야 한다.
				out.write(buffer, 0, read); // 웹 브라우저에 출력하기 위한 준비, 웹브라우저인지는 현재로서는 모르는 상태이다.
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}

