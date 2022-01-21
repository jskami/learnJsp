package com.it.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.NoticeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoticeServiceTest {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeService service;
	
	@Test
	public void testGetList() {
		service.getList().forEach(notice -> log.info(notice));
	}
		
	//@Test
	public void testRead() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(2);
		notice = service.read(notice);
		log.info(notice);
	}
	
	//@Test
	public void testInsert() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(5); // 있어도 없어도 넘버링은 자동 등록 확인-
		notice.setN_name("고대리");
		notice.setN_subject("육아휴직");
		notice.setN_contents("5월 부터 육아휴직 11개월");
		service.insert(notice);
		log.info(notice);
	}
	
	//@Test
	public void testUpdate() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(5);
		notice.setN_name("고대리");
		notice.setN_subject("육아휴직 + 사내복지");
		notice.setN_contents("육아휴직 기간 3개월 연장 + 산후조리원 이용금액 30% 할인");
		service.update(notice);
		log.info(notice);
	}
	
	//@Test
	public void testDelete() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(5);
		service.delete(notice);
		log.info(notice);
	}
	
}
