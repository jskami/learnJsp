package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.PageDTO;
import com.it.domain.Product2VO;
import com.it.mapper.Product2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class Product2ServiceImpl implements Product2Service {
	
	@Setter(onMethod_ = @Autowired)
	private Product2Mapper mapper;
	
	public Product2VO read(Product2VO product) {
		return mapper.read(product);
	}
	
	@Override
	public List<Product2VO> getList(PageDTO page) {
		return mapper.getList(page);
	}

	@Override // 오버라이드 어노테이션의 역할은 "너가 만들어놓고 왜 불러오질 못해!"라고 경고를 준다. 이름만 달라도 알려주니까-
	public void insert(Product2VO product) { 
		mapper.insert(product); 
	}
	
	@Override
	public void update(Product2VO product) {
		mapper.update(product);
	}

	@Override
	public void delete(Product2VO product) {
		mapper.delete(product);
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
}
