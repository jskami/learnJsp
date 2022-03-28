package com.it.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.Member2VO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class Member2ServiceTest {

	@Setter(onMethod_ = @Autowired)
	private Member2Service service;
	
	//@Test
	public void testRead() {
		Member2VO member = new Member2VO();
		member.setM_id("bird");
		member.setM_passwd("");
		member = service.read(member);
		log.info(member);
	}
	
	//@Test
	public void testAuth() {
		Member2VO member = new Member2VO();
		member.setM_id("tiger");
		member.setM_passwd("1234");
		service.auth(member);
	}
	
	//@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board)); // board는 아무 의미 없는것, 다른 이름으로 정해도 됨
	}
	
	@Test
	public void testInsert() {
		Member2VO member = new Member2VO();
		member.setM_id("lion");
		member.setM_passwd("1234");
		member.setM_name("사자");
		service.insert(member);
		log.info(member);
	}
	
	//@Test
	public void testUpdate() {
		Member2VO member = new Member2VO();
		member.setM_id("cat");
		member.setM_passwd("");
		member.setM_name("루랑이");
		service.update(member);
		log.info(member);
	}
	
	//@Test
	public void testDelete() {
		Member2VO member = new Member2VO();
		member.setM_id("삭제용");
		service.delete(member);
		log.info(member);
	}
}
