package com.it.mapper;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.it.domain.ProductVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ProductMapperTest {

	@Setter(onMethod_ = @Autowired)
	private ProductMapper mapper;
	
	//@Test
	public void testGetList() {
		mapper.getList().forEach(product -> log.info(product));
	}
	
	//@Test
	public void testInsert() {
		ProductVO product = new ProductVO();
//		product.setP_code(1008); 이거 없어도 자동생성인듯?
		product.setP_name("아이나비네비게이션");
		product.setP_price(298000);
		mapper.insert(product);
		log.info(product);
	}
	
	//@Test
	public void testRead() {
		ProductVO product = new ProductVO();
		product.setP_code(1005);
		product.setP_name("");
		product.setP_price(0);
		product = mapper.read(product);
		log.info(product);
	}
	
}
