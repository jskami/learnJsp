package com.it.mapper;

import java.util.List;

import com.it.domain.BoardVO;
import com.it.domain.PageDTO;

public interface BoardMapper {
	public List<BoardVO> getList(PageDTO page);
	
	public void insert(BoardVO board);
	
	public BoardVO read(BoardVO board);
	
	public void update(BoardVO board);
	
	public void delete(BoardVO board);
	
	public int getTotalCount();
}
