package com.it.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int pageNum;	// 현재 페이지
	private int pageAmount;	// 한 페이지에 보여줄 레코드 개수(=limit)
	
	public PageDTO() {
		this(1,10);		// 기본 생성자, 기본설정으로 현재 페이지 1, limit 10
	}
	
	public PageDTO(int pageNum, int pageAmount) {
		this.pageNum = pageNum;
		this.pageAmount = pageAmount;
	}

}
