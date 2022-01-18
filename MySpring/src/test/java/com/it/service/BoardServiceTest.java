package com.it.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	//@Test
	public void testRead() {
		BoardVO board = new BoardVO(); // ★빈 가방을 만들었다.★ 글 번호를 하나 받아서 읽어볼거야, 당연히 글이 있어야 겠지?! + 받기도 하고 주기도 하는 기능으로 만들어보자
		board.setB_num(1); // ★빈 가방에 1번을 넣었다.★
		board = service.read(board); // ★1번이 담긴 가방을 읽어낸다.★
		log.info(board);
	}
	
	//@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
	}
	
	//@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setB_subject("입력 테스트야");
		board.setB_name("둔둔이");
		board.setB_contents("노르웨이숲 크림색 고양이");
		service.insert(board);
		log.info(board);
	}
	
	//@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setB_num(1);
		board.setB_subject("블러드오렌지");
		board.setB_name("테이크 얼라이브");
		board.setB_contents("할인행사 해서 1000원입니다.");
		service.update(board);
		log.info(board);
	}
	
	//@Test
	public void testDelete() {
		BoardVO board = new BoardVO();
		board.setB_num(10);
		
		service.delete(board);
		log.info(board);
	}
		
}
