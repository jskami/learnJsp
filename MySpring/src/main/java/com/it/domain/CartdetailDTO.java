package com.it.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CartdetailDTO {
	private int cs_code;
	private int p_code;
	private String p_name;
	private int p_price;
	private int cs_cnt;
	private int cs_money; // 계산에 의해 얻어 낼 수 있지만 필드에 의해 처리하는게 훨씬 더 편하다. / 존재하지 않는 필드(cs_money)를 만들었다.(p_price x cs_snt)
	private int cm_code;
	private Date cs_rdate;
	private Date cs_udate;
	
}

// DTO는 VO와는 다르게 테이블을 따로 설계하지 않고 join해서 가져오는 방식을 말한다. https://youngjinmo.github.io/2021/04/dto-vo-entity/
