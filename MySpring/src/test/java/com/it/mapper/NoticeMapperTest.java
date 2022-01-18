package com.it.mapper;

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
@Log4j // 어노테이션은 아래 문장에 대한 지시어이기 때문에 반드시 붙어있어야 한다.
public class NoticeMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeMapper mapper;

	//@Test
	public void testGetList() { // 메서드를 만들자 (데이터를 가져오는 당위성 테스트 메서드)
		mapper.getList().forEach(notice -> log.info(notice)); // (람다식 문법으로 출력, 보드 자리는 아무 이름을 갖다 붙여도 됨, 로그는 출력 기능을 간소화, foreach를 통해 하나씩 하나씩 작업해서 한줄로 표현하겠다!-의 의미)
	}
	
	//@Test
	public void testInsert() {
		NoticeVO notice = new NoticeVO();
		notice.setN_subject("겨울바람");
		notice.setN_name("걷지못하는 동백꽃");
		notice.setN_contents("아름다운 모습으로 남아주렴");
		mapper.insert(notice); // DB로 전송!
		log.info(notice); // 프로그램 내 출력(확인용)!
	}
	
	//@Test
	public void testread() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(2);
		notice = mapper.read(notice); // 대상(데이터를 담고 있는 가방)을 지정하지 않으면 null값으로만 출력한다(멤버변수만 있는걸 불러온다는 뜻)
		log.info(notice);
	}
	
	//@Test
	public void testupdate() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(1);
		notice.setN_subject("수정 테스트");
		notice.setN_name("작성자");
		notice.setN_contents("스프링 테스트");
		mapper.update(notice);
	}
	
	@Test
	public void testdelete() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(1);
		
		mapper.delete(notice);
//		log.info(notice);
	}
	
}
