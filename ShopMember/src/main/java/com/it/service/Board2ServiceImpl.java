package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.Board2VO;
import com.it.domain.PageDTO;
import com.it.mapper.Board2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class Board2ServiceImpl implements Board2Service {

	@Setter(onMethod_ = @Autowired)
	private Board2Mapper mapper;
	
	@Override
	public Board2VO read(Board2VO board) { // read의 역할 : 특정 글에 대한 값을 받아 읽어준다.
		return mapper.read(board); // board를 넘겨주면 BoardMapper.xml의 read에서 #기호의 번호 하나만 받아서 연산해줄거야
	}
	
	@Override // 오버라이드 어노테이션의 역할은 "너가 만들어놓고 왜 불러오질 못해!"라고 경고를 준다. 이름만 달라도 알려주니까-
	public void insert(Board2VO board) { // '사용자가 입력'한 데이터를 가지고 insert하는 거야. 
		mapper.insert(board); // 매퍼의 인서트를 그대로 받아오면 된다.
	}
	
	@Override
	public List<Board2VO> getList(PageDTO page) { // 전체 레코드 조회 , List는 java.util로 선택
		return mapper.getList(page);
	}
	
	@Override
	public void update(Board2VO board) { // 데이터를 넘겨줘야 하므로 board를 넘겨준다.
		mapper.update(board); // return할게 없으므로 바로 업뎃 넘겨주면 돼
	}
	
	@Override
	public void delete(Board2VO board) {
		mapper.delete(board);
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
}
