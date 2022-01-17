package com.it.domain;

import java.util.Date;

import lombok.Data;

@Data // 어노테이션 추가, @Data는 멤버변수들에 대해서 자동으로 get, set 메서드를 만들어준다.
public class BoardVO {
	// 스프링의 기능을 활용, 멤버변수 생성
	private int b_num;
	private String b_subject;
	private String b_name;
	private String b_contents;
	private Date b_date; // 롬복으로 타입 지정해줘야 함, util타입-
}
