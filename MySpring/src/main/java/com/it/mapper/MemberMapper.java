package com.it.mapper;

import java.util.List;

import com.it.domain.MemberVO;

public interface MemberMapper {
	
	public List<MemberVO> getList();
	
	public void insert(MemberVO member); 
	
	public MemberVO read(MemberVO member); // ID로만 읽어내는 것

	//public MemberVO auth(MemberVO member); // ID와 PW로 읽어내는 것 / 활용하지 않음-
	
	public void update(MemberVO member);

	public void delete(MemberVO member);
}
