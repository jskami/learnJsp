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

import com.it.domain.Notice2VO;
import com.it.domain.PageDTO;
import com.it.domain.PageViewDTO;
import com.it.service.Notice2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/notice2/")
public class Notice2Controller {
	@Setter(onMethod_ = @Autowired)
	private Notice2Service service;
	
	@GetMapping("/list")
	public String list(Model model, PageDTO page, HttpSession session) { // list가 현재 자바 프로그램 내에서만 볼 수 있는 상태이기 때문에, 이녀석을 물고와서 웹으로 뿌려줘야 사용자도 list를 볼 수 있는데 이 역할을 해주는 녀석이 Model(모델)이다.
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/member2/login";
		} else {
		model.addAttribute("list", service.getList(page));
		int total = service.getTotalCount();
		PageViewDTO pageview = new PageViewDTO(page, total);
		model.addAttribute("pageview", pageview);
		} return "/notice2/list";
	}
	
	@GetMapping("/insert")
	public String insert(HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "/admin/login";
		} else {
			return "/notice2/insert";
		}
	}
	
	@PostMapping("/insert")
	public String insert(HttpServletRequest request, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "/admin/login";
		} else {
			DiskFileUpload upload = new DiskFileUpload();
			
			try {
				List list = upload.parseRequest(request);
				Iterator params = list.iterator();
				/*Iterator - ArrayList, HashSet을 반복하는데 사용할 수 있는 객체로
				 * next(), previous()로 이동 hashNext()다음요소 확인 기능이 있음
				 */
				
				String filepath = "C:\\myWorkspace\\learnJsp\\pds";
				
				Notice2VO notice = new Notice2VO();
				
				while(params.hasNext()) {
					FileItem item = (FileItem)params.next();
					if(item.isFormField()) {
						String fieldname = item.getFieldName();
						String fieldvalue = item.getString("utf-8");
						//itme의 input type에 저장된 값을 한글사용가능하도록 인코딩하여 String타입으로 fieldvaule 객체에 저장
						log.info(fieldname + ":" + fieldvalue);
						
						if(fieldname.equals("n_subject")) {
							notice.setN_subject(fieldvalue);
						}else if(fieldname.equals("n_name")) {
							notice.setN_name(fieldvalue);
						}else if(fieldname.equals("n_contents")) {
							notice.setN_contents(fieldvalue);
						}
					}else {
						//item의 next()가 form형태가 아닌 바이너리 파일이라면 절대경로값 지정
						String fname = item.getName();
						log.info(fname);
						if(fname!="") {
							notice.setN_file(fname);
							File file = new File(filepath + "/" + fname);
							log.info(file.getName());
							item.write(file);
						}
					}
				}
				log.info("insert Notice"+request);
				service.insert(notice);
			}catch(Exception e) {
				System.out.println(e);
			}
			return "redirect:/notice2/list";
	}
	
}
	
	@GetMapping("/downLoad")
	public void download(Notice2VO notice, HttpServletResponse response) {
		
			notice = service.read(notice);
			
			try {
				String filepath = "c:\\myWorkspace\\learnJsp\\pds\\"+notice.getN_file();
				File file = new File(filepath);
				
				String newName = new String(file.getName().getBytes("UTF-8"),"ISO-8859-1");
				//한글 처리 가능하도록 file name을 byte타입으로 인코딩
				
				response.setHeader("Content-Disposition", "attachment;filename="+newName);
				log.info(file.getName());
				
				FileInputStream fis = new FileInputStream(filepath);
				OutputStream out = response.getOutputStream();
				
				int read = 0;
				byte[] buffer = new byte[1024];
				
				while((read = fis.read(buffer)) != -1) {
					out.write(buffer, 0, read); // buffer의 처음부터 read에 저장된 값만큼 웹 브라우저에 출력
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
	}
	
	@GetMapping("/view")
	public String view(Notice2VO notice, Model model, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
		 return "/admin/login";
		} else {
		log.info("--------읽기 전--------");
		log.info(notice);
		notice = service.read(notice);
		log.info("--------읽은 후--------");
		log.info(notice);
		model.addAttribute("notice", notice);
		model.addAttribute("page", page);
		} return "/notice2/view";
	}
	
	@GetMapping("/update")
	public void update(Notice2VO notice, Model model, PageDTO page) {
		log.info("---------업데이트를 위한 번호--------");
		log.info(notice);
		notice = service.read(notice);
		log.info("---------업데이트를 위한 번호--------");
		log.info(notice);
		model.addAttribute("notice", notice);
		model.addAttribute("page", page);
	}
	
	@PostMapping("/update")
	public String update(HttpServletRequest request, PageDTO page) {
		DiskFileUpload upload = new DiskFileUpload();
		Notice2VO notice = new Notice2VO();
		try {
			List items = upload.parseRequest(request);
			Iterator params = items.iterator();
			
			String filepath = "C:\\myWorkspace\\learnJSP\\pds";
			
			
			while(params.hasNext()) {
				FileItem item = (FileItem)params.next();
				if(item.isFormField()) {
					String fieldname = item.getFieldName();
					String fieldvalue = item.getString("utf-8");
					log.info(fieldname + ":" + fieldvalue );
					
					if(fieldname.equals("n_subject")) {
						notice.setN_subject(fieldvalue);
					} else if(fieldname.equals("n_name")) {
						notice.setN_name(fieldvalue);
					} else if(fieldname.equals("n_contents")) {
						notice.setN_contents(fieldvalue);
					} else if(fieldname.equals("n_num")) {
						notice.setN_num(Integer.parseInt(fieldvalue));
					} else if(fieldname.equals("n_file")) {
						notice.setN_file(fieldvalue);
					}
				} else {
					String fname = item.getName();
					log.info("바이너리 파일이름 : " + fname);
					if (fname != null) {
						File file = new File(filepath + "/" + fname);
						notice.setN_file(fname);
						item.write(file);
					}
				}
			} 
			service.update(notice);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		return "redirect:/notice2/view?n_num=" + notice.getN_num() + "&pageNum=" + page.getPageNum();
	}

	@GetMapping("/delete")
	public String delete(Notice2VO notice, PageDTO page, HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if(a_id == null) {
			return "redirect:/admin/login";
		} else {
		log.info("--------삭제--------");
		service.delete(notice);
		}return "redirect:/notice2/list";
	}
}
