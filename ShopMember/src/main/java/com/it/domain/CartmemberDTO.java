package com.it.domain;

import lombok.Data;

@Data
public class CartmemberDTO {
	private int cm_code; // 영수증 번호
	private String m_id; // 사용자
	private String m_name; // 사용자 이름
	private int cm_total;
}
