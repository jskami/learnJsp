package com.it.mapper;

import java.util.List;

import com.it.domain.PageDTO;
import com.it.domain.Product2VO;

public interface Product2Mapper {
	
	public List<Product2VO> getList(PageDTO page);
	
	public Product2VO read(Product2VO product);
	
	public void insert(Product2VO product);
	
	public void update(Product2VO product);
	
	public void delete(Product2VO product);
	
	public int getTotalCount();
}
