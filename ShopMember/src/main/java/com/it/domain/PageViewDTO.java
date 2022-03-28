package com.it.domain;

import lombok.Data;

@Data
public class PageViewDTO {
	private int startPage;
	private int endPage;
	private int realend;
	private boolean prev;
	private boolean next;
	
	private int total;
	
	PageDTO page; // 입력된 page 로 나머지 변수를 계산
	
	public PageViewDTO(PageDTO page, int total) { // 현재 페이지와 전체 레코드 개수 전달
		this.page = page;
		this.total = total;
		this.endPage = (int)Math.ceil(page.getPageNum() / 10.0) * 10;
		this.startPage = this.endPage - 10 + 1;
		this.realend = (int)Math.ceil(total / (double)page.getPageAmount()); //실제 마지막 페이지 번호
		if (this.realend < this.endPage) {
			this.endPage = this.realend;
		}
		
		this.prev = this.startPage > 1;  // 최소한 시작이 11페이지일 경우 참
		this.next = this.endPage < this.realend;
	}
}
