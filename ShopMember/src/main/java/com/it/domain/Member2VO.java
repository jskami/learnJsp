package com.it.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Member2VO {
	private String m_id;
	private	String m_passwd;
	private String m_name;
	private int m_phone;
	private String m_email;
	private Date m_rdate;
	private Date m_udate;
}
