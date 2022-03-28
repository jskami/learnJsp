package com.it.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Notice2VO {
	private int n_num;
	private String n_subject;
	private String n_name;
	private String n_contents;
	private String n_file;
	private Date n_date;
}
