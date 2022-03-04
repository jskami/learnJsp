package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.BoardVO;
import com.it.domain.PageDTO;
import com.it.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j // 첫 번째로 작성후 저장,
@Service // 두 번째로 작성후 저장, // @Service : 방금 만든 서비스인터페이스에 대해 작업한다는 의미, 어노테이션이 해주는 역할은 개발자가 일일이 타이핑 해야 하는 것들을 자동으로 코드를 생성해주는 기능이다. 저번주에 학습하고 정리했으니까 구글 문서 확인해!
public class BoardServiceImpl implements BoardService { // 이 클래스에서는 서비스 인터페이스에서 만든 5개의 메서드를 구현해야 한다.
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Override
	public BoardVO read(BoardVO board) { // read의 역할 : 특정 글에 대한 값을 받아 읽어준다.
		return mapper.read(board); // board를 넘겨주면 BoardMapper.xml의 read에서 #기호의 번호 하나만 받아서 연산해줄거야
	}
	
	@Override // 오버라이드 어노테이션의 역할은 "너가 만들어놓고 왜 불러오질 못해!"라고 경고를 준다. 이름만 달라도 알려주니까-
	public void insert(BoardVO board) { // '사용자가 입력'한 데이터를 가지고 insert하는 거야. 
		mapper.insert(board); // 매퍼의 인서트를 그대로 받아오면 된다.
	}
	
	@Override
	public List<BoardVO> getList(PageDTO page) { // 전체 레코드 조회 , List는 java.util로 선택
		return mapper.getList(page);
	}
	
	@Override
	public void update(BoardVO board) { // 데이터를 넘겨줘야 하므로 board를 넘겨준다.
		mapper.update(board); // return할게 없으므로 바로 업뎃 넘겨주면 돼
	}
	
	@Override
	public void delete(BoardVO board) {
		mapper.delete(board);
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
}
