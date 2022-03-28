package com.it.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.it.service.Cart2Service;
import com.it.service.Member2Service;
import com.it.service.Order2Service;
import com.it.service.Product2Service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/order2/")
public class OrderController {
	
	@Setter(onMethod_ = @Autowired)
	private Product2Service productservice;
	
	@Setter(onMethod_ = @Autowired)
	private Member2Service memberservice;
	
	@Setter(onMethod_ = @Autowired)
	private Cart2Service cartservice;
	
	@Setter(onMethod_ = @Autowired)
	private Order2Service orderserivce;
	
	@GetMapping("/list")
	public String list(HttpSession session) {
		String a_id = (String)session.getAttribute("a_id");
		if (a_id == null) {
			return "redirect:/admin/login";
		} else {
			return "/order2/list";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
