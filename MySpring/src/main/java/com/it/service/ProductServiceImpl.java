package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.ProductVO;
import com.it.mapper.ProductMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Setter(onMethod_ = @Autowired)
	private ProductMapper mapper;
	
	@Override
	public ProductVO read(ProductVO product) { // read의 역할 : 특정 글에 대한 값을 받아 읽어준다.
		return mapper.read(product); // member를 넘겨주면 MemberMapper.xml의 read에서 #기호의 번호 하나만 받아서 연산해줄거야
	}
	
	@Override // 오버라이드 어노테이션의 역할은 "너가 만들어놓고 왜 불러오질 못해!"라고 경고를 준다. 이름만 달라도 알려주니까-
	public void insert(ProductVO product) { 
		mapper.insert(product); 
	}
	
	@Override
	public List<ProductVO> getList() {
		return mapper.getList();
	}

	@Override
	public void update(ProductVO product) {
		
	}

	@Override
	public void delete(ProductVO product) {
		
	}
	
}
