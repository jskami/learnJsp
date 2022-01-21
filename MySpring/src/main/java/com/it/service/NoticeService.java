package com.it.service;

import java.util.List;

import com.it.domain.NoticeVO;

public interface NoticeService {

	public List<NoticeVO> getList();
	
	public void insert(NoticeVO notice);
	
	public NoticeVO read(NoticeVO notice);
	
	public void update(NoticeVO notice);
	
	public void delete(NoticeVO notice);
}
