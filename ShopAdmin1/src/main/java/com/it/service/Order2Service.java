package com.it.service;

import java.util.List;

import com.it.domain.Cartmain2VO;
import com.it.domain.OrderdetailDTO;
import com.it.domain.Ordermain2VO;
import com.it.domain.OrdermemberDTO;

public interface Order2Service {
	
	public Ordermain2VO orderproc(Cartmain2VO cartmain); // 오더지만 카트 정보가 필요하다.
	
	public List<OrderdetailDTO> getListOrderDetail(Ordermain2VO ordermain); // 주문 상세 내용을 처리 하기위한 메서드
	
	public Ordermain2VO readmainid(Ordermain2VO ordermain);
	
	public OrdermemberDTO getOrderTotal(Ordermain2VO ordermain);
}
