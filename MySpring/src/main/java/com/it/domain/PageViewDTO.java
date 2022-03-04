package com.it.domain;

import lombok.Data;

@Data
public class PageViewDTO {  // 입력받은 pageNum과 pageAmount의 결과를 나타내는 클래스
	private int startPage;
	private int endPage;
	private int realend;
	private boolean prev; // 전이든 후든 다음 페이지로 갈 수 없는데 가는건 좋지 않으니 boolean 타입으로 해준다. 
	private boolean next;
	
	private int total;
	PageDTO page; // 입력된 page로 나머지 변수를 계산
	
	public PageViewDTO(PageDTO page, int total) { // 생성자를 만들어보자. 컨트롤러를 거쳐 정보가 들어올것이다. 현재 페이지와 전체 레코드 개수 전달
		this.page = page;
		this.total = total; // 전체 레코드의 개수
		this.endPage = (int)Math.ceil(page.getPageNum() / 10.0) * 10; // endPage를 계산해보자.
		this.startPage = this.endPage - 10 + 1; // 하단부에 보이는 링크의 개수
		
		this.realend = (int)Math.ceil(total / (double)page.getPageAmount()); //실제 마지막이라는 의미로 임시변수 만들고, 마지막 페이지의 번호
		if(this.realend < this.endPage) {
			this.endPage = this.realend;
		}
		
		this.prev = this.startPage > 1;  // 최소한 시작페이지가 11페이지일 경우 참
		this.next = this.endPage < this.realend;
		
	}	
		
		
			
}
