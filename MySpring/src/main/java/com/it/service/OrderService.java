package com.it.service;

import java.util.List;

import com.it.domain.CartmainVO;
import com.it.domain.OrderdetailDTO;
import com.it.domain.OrdermainVO;
import com.it.domain.OrdermemberDTO;

public interface OrderService {

	public OrdermainVO orderproc(CartmainVO cartmain); // 오더지만 카트 정보가 필요하다.
	
	public List<OrderdetailDTO> getListOrderDetail(OrdermainVO ordermain); // 주문 상세 내용을 처리 하기위한 메서드
	
	public OrdermemberDTO getOrderTotal(OrdermainVO ordermain);
	
	
}
