package com.it.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int pageNum; // 실제 보고있는 페이지의 번호, offset에 관련되어 있는 것
	private int pageAmount; // 한 화면에 보여지는 레코드의 개수 , limit에 관련되어 있는 것
	
	public PageDTO() { // 디폴트 생성자
		this(1, 10); // 아래의 매개변수 생성자 두 개를 이용해서 설정한다. (pageNum, pageAmount)
	}
	
	public PageDTO(int pageNum, int pageAmount) { // 두 개의 정보를 가지는 생성자를 만든다. 정보가 아무것도 없는 상태도 보여줘야 하니까
		this.pageNum = pageNum; // 값을 가지고 들어온다면 pageAmount는 그대로, pageNum은 넘어온 값에서 -1
		this.pageAmount = pageAmount;
		
	}

}
