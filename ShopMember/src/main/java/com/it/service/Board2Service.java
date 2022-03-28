package com.it.service;

import java.util.List;

import com.it.domain.Board2VO;
import com.it.domain.PageDTO;

public interface Board2Service {

	public List<Board2VO> getList(PageDTO page); // 전체 레코드 조회, 리스트로 받을거야, 여러개의 레코드가 필요하지! 제네릭 문법으로 메서드를 만들어보자

	public void insert(Board2VO board); // 레코드 입력

	public Board2VO read(Board2VO board); // 단일 레코드 조회, board 가방에 반환할거야
	
	public void update(Board2VO board); // 레코드 수정(업데이트)
	
	public void delete(Board2VO board); // 레코드 삭제(딜리트)
	
	public int getTotalCount();
}
