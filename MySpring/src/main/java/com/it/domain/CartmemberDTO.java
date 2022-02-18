package com.it.domain;

import lombok.Data;

@Data
public class CartmemberDTO {
	private int cm_code; // 영수증 번호
	private String m_id; // 사용자
	private String m_name; // 사용자 이름
	private int cm_total; // 카트서브를 합친 총 금액, VO에 total이 없으니까 DTO로 사용할거야
}
