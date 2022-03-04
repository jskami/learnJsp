package com.it.mapper;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.NoticeVO;
import com.it.domain.PageDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // 현재로서는 어노테이션 기능을 쓸 수 없어서 텍스트형식으로 써야 하는데... 일단 작성해보고 안되면 물어봐야겠다.
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoticeMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private NoticeMapper mapper;
	
	@Test
	public void testGetList() {
		PageDTO page = new PageDTO(2,10);
		mapper.getList(page).forEach(notice -> log.info(notice));
	}
	
	//@Test
	public void testInsert() {
		NoticeVO notice = new NoticeVO();
		notice.setN_subject("개발 3팀");
		notice.setN_name("이팀장");
		notice.setN_contents("부서 이동 및 승진");
		mapper.insert(notice);
		log.info(notice);
	}
	
	//@Test
	public void testread() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(2);
		notice = mapper.read(notice);
		log.info(notice);
	}
	
	//@Test
	public void testUpdate() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(1);
		notice.setN_name("고팀장");
		notice.setN_subject("개발 2팀");
		notice.setN_contents("인사발령");
		mapper.update(notice);
	}
	
	//@Test
	public void testdelete() {
		NoticeVO notice = new NoticeVO();
		notice.setN_num(4);
		mapper.delete(notice);
		log.info(notice);
	}
	
}
