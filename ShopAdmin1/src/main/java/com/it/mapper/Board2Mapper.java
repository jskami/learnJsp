package com.it.mapper;

import java.util.List;

import com.it.domain.Board2VO;
import com.it.domain.PageDTO;

public interface Board2Mapper {
	
	public List<Board2VO> getList(PageDTO page); // 제네릭 문법으로 <>안에 타입 입력 -> 반환, 보드vo를 배열형태로 리스팅하겠다!-의 의미 
		
	public void insert(Board2VO board); // BoardVO의 데이터를 받아 인서트 할 이름을 board라 정한거야. CRUD중 C한 거야
	
	public Board2VO read(Board2VO board); // 보통 번호를 받아서 넘기는데 ex.(int b_num) 오히려 단독으로 번호만 받아서 처리하는게 위험하다.(왜인지는...)

	public void update(Board2VO board); // 업데이트는 반환 받을게 없어 그러니까 void!

	public void delete(Board2VO board); // 삭제 역시 반환 받을게 없다. 글 번호를 받아서 삭제하긴 할거야
	
	public int getTotalCount();
	
}
