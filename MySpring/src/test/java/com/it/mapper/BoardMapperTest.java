package com.it.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.BoardVO;
import com.it.domain.PageDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j // 어노테이션은 아래 문장에 대한 지시어이기 때문에 반드시 붙어있어야 한다.
public class BoardMapperTest {
	
	@Setter(onMethod_ = @Autowired) // 어노테이션은 '반드시' 멤버변수 바로 위에 입력해야한다. '의존성 주입'이라 한다.
	private BoardMapper mapper; // 어노테이션세터는 생성하지 않았지만 객체 역할을 할 수 있도록 설정 해주는 것이다.
								// BoardMapper는 인터페이스 파일인데 이녀석을 mapper라는 이름의 객체로 만들어서 사용하는 것이다.
		
	//@Test
	public void testGetList() { // 메서드를 만들자 (데이터를 가져오는 당위성 테스트 메서드)
		PageDTO page = new PageDTO(2,10);
		mapper.getList(page).forEach(board -> log.info(board)); // (람다식 문법으로 출력, 보드 자리는 아무 이름을 갖다 붙여도 됨, 로그는 출력 기능을 간소화, foreach를 통해 하나씩 하나씩 작업해서 한줄로 표현하겠다!-의 의미)
	}
	
	//@Test
	public void testInsert() {
		BoardVO board = new BoardVO(); // 위에 맵퍼를 통해서 데이터를 넣는다. 빈 가방 생성
		board.setB_subject("게시판 테스트입니다.");
		board.setB_name("블러드오렌지");
		board.setB_contents("게시판이다.");
		mapper.insert(board); // 아래에서 제대로 출력된 것을 확인했으면 로그인포를 지우거나 주석하고 맵퍼를 테스트하자! / DB로 전송!
		log.info(board); // System.out.plintln()과 같은 의미이다. 위에서 맵퍼를 통해 바로 DB에 저장하지말고 로그인포를 통해 제대로 출력되는지 확인먼저 하고 데이터를 저장하자! / 프로그램 내 출력(확인용)!
	}
	
	//@Test
	public void testRead() {
		BoardVO board = new BoardVO(); // 빈 가방 생성
		board.setB_num(2); // 이제 하나만 선택해서 볼거야(=set), 2번을 VO객체에 저장
		
		board = mapper.read(board); // read 메서드 호출해서 객체 반환 / 대상(데이터를 담고 있는 가방)을 지정하지 않으면 null값으로만 출력한다(멤버변수만 있는걸 불러온다는 뜻)
		log.info(board);
	}
	
	//@Test
	public void testUpdate() {
		BoardVO board = new BoardVO();
		board.setB_num(2); // 수정 조건 where / 2번을 수정하겠다!
		board.setB_subject("게시판 수정할거야");
		board.setB_name("스트로베리");
		board.setB_contents("스프링이다.");
		mapper.update(board);
	}
	
	//@Test
	public void testdelete() {
		BoardVO board = new BoardVO();
		board.setB_num(5); // 삭제 조건 where / 5번을 수정하겠다!
		
		mapper.delete(board); // 가방에 번호 하나를 넣고 삭제하기로 한거야-
		//log.info(board);
	}
	
}
