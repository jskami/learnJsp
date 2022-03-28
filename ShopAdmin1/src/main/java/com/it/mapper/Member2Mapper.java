package com.it.mapper;

import java.util.List;

import com.it.domain.Member2VO;

public interface Member2Mapper {
	public List<Member2VO> getList();
	
	public void insert(Member2VO member); 
	
	public Member2VO read(Member2VO member); // ID로만 읽어내는 것

	//public Member2VO auth(Member2VO member); // ID와 PW로 읽어내는 것 / 활용하지 않음-
	
	public void update(Member2VO member);

	public void delete(Member2VO member);
}
