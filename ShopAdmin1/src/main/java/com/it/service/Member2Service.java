package com.it.service;

import java.util.List;

import com.it.domain.Member2VO;

public interface Member2Service {
	
	public List<Member2VO> getList(); 

	public void insert(Member2VO member);

	public Member2VO read(Member2VO member); 
	
	public boolean auth(Member2VO member);  // ID와 PW를 전달하여 인증 처리 후 true / false로 반환
	
	public void update(Member2VO member);
	
	public void delete(Member2VO member);
}
