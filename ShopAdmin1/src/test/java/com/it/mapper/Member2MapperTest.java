package com.it.mapper;

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
public class Member2MapperTest {

	@Setter(onMethod_ = @Autowired)
	private Member2Mapper mapper;
	
	//@Test
	public void testGetList() {
		mapper.getList().forEach(member -> log.info(member));
	}
	
	//@Test
	public void testInsert() {
		Member2VO member = new Member2VO();
		member.setM_id("koriri");
		member.setM_name("코리리");
		member.setM_passwd("1234");
		member.setM_phone(01011114444);
		member.setM_email("koriri@com");
		mapper.insert(member);
		log.info(member);
	}
	
	//@Test
	public void testRead() {
		Member2VO member = new Member2VO();
		member.setM_id("kururu");
		member = mapper.read(member);
		log.info(member);
	}
	
	//@Test
	public void testUpdate() {
		Member2VO member = new Member2VO();
		member.setM_id("koriri");
		member.setM_name("코코볼");
		member.setM_passwd("1234");
		member.setM_phone(00012123333);
		member.setM_email("kokobol@com");
		mapper.update(member);
		log.info(member);
	}
	
	//@Test
	public void testDelete() {
		Member2VO member = new Member2VO();
		member.setM_id("koriri");
		mapper.delete(member);
		log.info(member);
	}
	
	
	
	
	
	
	
	
	
	
}
