package com.it.mapper;

import java.util.List;

import com.it.domain.NoticeVO;
import com.it.domain.PageDTO;

public interface NoticeMapper {
	
	public List<NoticeVO> getList(PageDTO page);
	
	public void insert(NoticeVO notice);
	
	public NoticeVO read(NoticeVO notice);
	
	public void update(NoticeVO notice);
	
	public void delete(NoticeVO notice);
	
	public int getTotalCount();
}
