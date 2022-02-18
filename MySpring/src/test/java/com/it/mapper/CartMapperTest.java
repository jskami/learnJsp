package com.it.mapper;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.CartdetailDTO;
import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CartMapperTest {
	
	@Setter(onMethod_ = @Autowired)
	private CartMapper mapper;

	//@Test
	public void testGetListMain() {
		mapper.getListmain().forEach(cartmain -> log.info(cartmain));
	}

	//@Test
	public void testGetListSub() {
		mapper.getListsub().forEach(cartsub -> log.info(cartsub));
	}
	
	//@Test
	public void testInsertMain() {				// 1. tiger유저(1003)가 장바구니를 사용할거야
		CartmainVO cartmain = new CartmainVO();
		cartmain.setM_id("tiger");
		mapper.insertMain(cartmain);
		log.info(cartmain);
	}
	
	//@Test
	public void testInsertSub() {				// 2. tiger유저는 1001번 상품 2개를 cartsub에 넣을거야!
		CartsubVO cartsub = new CartsubVO();
		cartsub.setCm_code(1003);
		cartsub.setP_code(1001);
		cartsub.setCs_cnt(2);
		mapper.insertSub(cartsub);
		cartsub.setCm_code(1003);
		cartsub.setP_code(1002);
		cartsub.setCs_cnt(3);
		mapper.insertSub(cartsub);
		log.info(cartsub);
	}
	
	//@Test
	public void testDeleteSub() {				// 3. tiger유저가 cartsub에 넣은 1001번 상품과 1002번 상품이 cartsub에서는 1005, 1006번으로 등록되었고, 이제 삭제가 되는지 테스트 하는거야
		CartsubVO cartsub = new CartsubVO();
		cartsub.setCs_code(1005);
		mapper.deleteSub(cartsub);
		cartsub.setCs_code(1006);
		mapper.deleteSub(cartsub);
	}
	
	//@Test
	public void testDeleteMain() {				// 4. tiger유저(1003)를 cartsub에서 지운다.
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(1003);
		mapper.deleteMain(cartmain);
	}
	
	//@Test
	public void testCartdetailTest() {
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(1004);
		List<CartdetailDTO> cartdetail = mapper.getListCartDetail(cartmain); // new가 아닌 리턴으로 받아오면 돼
		cartdetail.forEach(cd -> log.info(cd));
	}
	
	//@Test
	public void testCartTotal() {
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(1004);
		CartmemberDTO cartmember = mapper.getCartTotal(cartmain);
		log.info(cartmember);
	}
	
	
	
}
