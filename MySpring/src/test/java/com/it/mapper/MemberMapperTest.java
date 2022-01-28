package com.it.mapper;

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
public class MemberMapperTest {
	
	@Setter(onMethod_ = @Autowired) // 어노테이션은 '반드시' 멤버변수 바로 위에 입력해야한다. '의존성 주입'이라 한다.
	private MemberMapper mapper; // 어노테이션세터는 생성하지 않았지만 객체 역할을 할 수 있도록 설정 해주는 것이다.
								// BoardMapper는 인터페이스 파일인데 이녀석을 mapper라는 이름의 객체로 만들어서 사용하는 것이다.
		
	//@Test
	public void testGetList() { // 메서드를 만들자 (데이터를 가져오는 당위성 테스트 메서드)
		mapper.getList().forEach(member -> log.info(member)); // (람다식 문법으로 출력, 보드 자리는 아무 이름을 갖다 붙여도 됨, 로그는 출력 기능을 간소화, foreach를 통해 하나씩 하나씩 작업해서 한줄로 표현하겠다!-의 의미)
	}
	
	//@Test
	public void testInsert() {
		MemberVO member = new MemberVO();
		member.setM_id("frog");
		member.setM_passwd("2222");
		member.setM_name("짹짹이");
		mapper.insert(member);
		log.info(member);
	}
	
	//@Test
	public void testRead() {
		MemberVO member = new MemberVO();
		member.setM_id("cat");
		member.setM_passwd("");
		member = mapper.read(member); 
		log.info(member);
	}
	
	//@Test
	public void testUpdate() {
		MemberVO member = new MemberVO();
		member.setM_id("forg");
		member.setM_passwd("3333");
		member.setM_name("케로로");
		mapper.update(member);
		log.info(member);
	}
	
	//@Test
	public void testdelete() {
		MemberVO member = new MemberVO();
		member.setM_id("frog");
		mapper.delete(member);
		log.info(member);
	}
	
}
