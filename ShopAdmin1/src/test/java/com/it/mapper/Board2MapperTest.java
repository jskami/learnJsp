package com.it.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.Board2VO;
import com.it.domain.PageDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class Board2MapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private Board2Mapper mapper;
	
	//@Test
	public void TestGetList() {
		PageDTO page = new PageDTO(2,10);
		mapper.getList(page).forEach(board -> log.info(board));
	}
	
	//@Test
	public void testInsert() {
		Board2VO board = new Board2VO();
		board.setB_subject("입력테스트 제목");
		board.setB_name("입력테스트 작성자");
		board.setB_contents("입력테스트 내용");
		mapper.insert(board);
		log.info(board);
	}
	
	//@Test
	public void testRead() {
		Board2VO board = new Board2VO();
		board.setB_num(2);
		board = mapper.read(board);
		log.info(board);
	}
	
	//@Test
	public void testupdate() {
		Board2VO board = new Board2VO();
		board.setB_num(2);
		board.setB_subject("크라운");
		board.setB_name("빅파이");
		board.setB_contents("과자입니다.");
		mapper.update(board);
	}
	
	//@Test
	public void testdelete() {
		Board2VO board = new Board2VO();
		board.setB_num(0);
		mapper.delete(board);
	}
	
	
	
	
	
	
	
	
}
