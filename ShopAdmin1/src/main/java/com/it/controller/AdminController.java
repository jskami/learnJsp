package com.it.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.domain.AdminVO;
import com.it.service.AdminService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/")
public class AdminController {
	
	@Setter(onMethod_ = @Autowired)
	private AdminService service;
	
	@GetMapping("/login")
	public void login() {
		//로그인 페이지 호출
	}
	
	@PostMapping("/login")
	public void login(AdminVO admin, HttpSession session, HttpServletResponse response) throws IOException { // 로그인에서 넘어오는 데이터를 memberVO의 member가방에 담을거야. 세션객체의 세션변수를 지정해준다.
		log.info(admin);
		
		response.setContentType("text/html; charset=euc-kr");
		PrintWriter out = response.getWriter();
	
		if (session.getAttribute("m_id") != null) {
			out.println("<script>alert('이미 사용자로 로그인되었습니다'); history.go(-1); </script>");
			out.flush();
		} else {
			boolean chk = service.auth(admin); // 서비스에 로그인 정보를 건네준다. // 현재 서비스에 로그인이 찍히지 않으니까 여기서 해보자. // 체크변수를 불리언 타입으로 만들어서 확인해보자.
			if (chk == true) {
//				admin = service.read(admin); // read.(member)에서 멤버에는 id와pw가 들어있다.
				session.setAttribute("a_id", admin.getA_id()); // 세션변수 생성
				out.println("<script>alert('로그인 성공'); location.href='/' </script>");
				out.flush();
				log.info("인증성공");
//				return "redirect:/";
				} else {
			out.println("<script>alert('로그인 실패'); history.go(-1); </script>");
			out.flush();
			}
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 이 메서드로 세션을 끊는다.(관련된 모든 세션변수가 끊어진다.)
		return "redirect:/"; // 최상위 위치 밑에 홈으로 반환 가는거야
	}
	
}
