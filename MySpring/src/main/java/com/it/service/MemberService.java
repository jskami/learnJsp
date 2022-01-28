package com.it.service;

import java.util.List;

import com.it.domain.MemberVO;

public interface MemberService {

	public List<MemberVO> getList(); 

	public void insert(MemberVO member);

	public MemberVO read(MemberVO member); 
	
	public boolean auth(MemberVO member);  // ID와 PW를 전달하여 인증 처리 후 true / false로 반환
	
	public void update(MemberVO member);
	
	public void delete(MemberVO member);
	
}
