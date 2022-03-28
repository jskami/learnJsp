package com.it.service;

import java.util.List;

import com.it.domain.Notice2VO;
import com.it.domain.PageDTO;

public interface Notice2Service {
	public List<Notice2VO> getList(PageDTO page);
	
	public void insert(Notice2VO notice);
	
	public Notice2VO read(Notice2VO notice);
	
	public void update(Notice2VO notice);
	
	public void delete(Notice2VO notice);
	
	public int getTotalCount();
}
