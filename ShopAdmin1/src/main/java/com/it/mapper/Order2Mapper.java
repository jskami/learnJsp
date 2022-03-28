package com.it.mapper;

import java.util.List;

import com.it.domain.OrderdetailDTO;
import com.it.domain.Ordermain2VO;
import com.it.domain.OrdermemberDTO;
import com.it.domain.Ordersub2VO;

public interface Order2Mapper {

	public void insertmain(Ordermain2VO ordermain);
	
	public void insertsub(Ordersub2VO ordersub);

//	public Ordermain2VO readmain(Ordermain2VO ordermain);
	
	public Ordermain2VO readmainid(Ordermain2VO ordermain);
	
	public List<OrderdetailDTO> getListOrderDetail(Ordermain2VO ordermain);
	
	public OrdermemberDTO getOrderTotal(Ordermain2VO Ordermain);
}
