package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.Notice2VO;
import com.it.domain.PageDTO;
import com.it.mapper.Notice2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class Notice2ServiceImpl implements Notice2Service {

	@Setter(onMethod_ = @Autowired)
	private Notice2Mapper mapper;
	
	@Override
	public Notice2VO read(Notice2VO notice) {
		return mapper.read(notice);
	}
	
	@Override
	public void insert(Notice2VO notice) {
		mapper.insert(notice);
	}
	
	@Override
	public List<Notice2VO> getList(PageDTO page) {
		return mapper.getList(page);
	}
	
	@Override
	public void update(Notice2VO notice) {
		mapper.update(notice);
	}
	
	@Override
	public void delete(Notice2VO notice) {
		mapper.delete(notice);
	}
	
	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}
	
}
