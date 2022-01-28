package com.it.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;
	
	//@Test
	public void testRead() {
		MemberVO member = new MemberVO();
		member.setM_id("bird");
		member.setM_passwd("");
		member = service.read(member);
		log.info(member);
	}
	
	@Test
	public void testAuth() {
		MemberVO member = new MemberVO();
		member.setM_id("tiger");
		member.setM_passwd("1234"); // 여기 뭔가 이상하다?
		service.auth(member);
	}
	
	//@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	//@Test
	public void testInsert() {
		MemberVO member = new MemberVO();
		member.setM_id("삭제용");
		member.setM_passwd("0000");
		member.setM_name("땡땡땡");
		service.insert(member);
		log.info(member);
	}
	
	//@Test
	public void testUpdate() {
		MemberVO member = new MemberVO();
		member.setM_id("cat");
		member.setM_passwd("");
		member.setM_name("루랑이");
		service.update(member);
		log.info(member);
	}
	
	//@Test
	public void testDelete() {
		MemberVO member = new MemberVO();
		member.setM_id("삭제용");
		service.delete(member);
		log.info(member);
	}
	
}
