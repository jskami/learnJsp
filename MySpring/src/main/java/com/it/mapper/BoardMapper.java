package com.it.mapper;

import java.util.List;

import com.it.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getList(); // 제네릭 문법으로 <>안에 타입 입력 -> 반환, 보드vo를 배열형태로 리스팅하겠다!-의 의미 
		
	public void insert(BoardVO board); // BoardVO의 데이터를 받아 인서트 할 이름을 board라 정한거야. CRUD중 C한 거야
	
	public BoardVO read(BoardVO board); // 보통 번호를 받아서 넘기는데 ex.(int b_num) 오히려 단독으로 번호만 받아서 처리하는게 위험하다.(왜인지는...)

	public void update(BoardVO board); // 업데이트는 반환 받을게 없어 그러니까 void!

	public void delete(BoardVO board); // 삭제 역시 반환 받을게 없다. 글 번호를 받아서 삭제하긴 할거야
}
