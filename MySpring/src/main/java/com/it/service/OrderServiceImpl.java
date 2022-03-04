package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.CartmainVO;
import com.it.domain.CartsubVO;
import com.it.domain.OrderdetailDTO;
import com.it.domain.OrdermainVO;
import com.it.domain.OrdermemberDTO;
import com.it.domain.OrdersubVO;
import com.it.mapper.CartMapper;
import com.it.mapper.OrderMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class OrderServiceImpl implements OrderService {

	@Setter(onMethod_ = @Autowired)
	private OrderMapper ordermapper;
	
	@Setter(onMethod_ = @Autowired)
	private CartMapper cartmapper;
	
	@Override
	public OrdermainVO orderproc(CartmainVO cartmain) { // om_code 반환(주문리스트에서 사용하기 위함)
		// 주문main insert 신규 생성
		// cm_code를 사용하여 장바구니 sub 조회
		// 주문main insert
		// om_code 조회
		// 주문sub insert
		// 장바구니sub 삭제(cm_code 활용)
		// 장바구니main 삭제(cm_code 활용)
		// om_code를 반환
		
		OrdermainVO ordermain = new OrdermainVO();
		ordermain.setM_id(cartmain.getM_id());
		
		ordermapper.insertmain(ordermain);
		ordermain = ordermapper.readmainid(ordermain); // 역순으로 조회하여 가장 큰 om_code
		
		List<CartsubVO> cartsub = cartmapper.getListCart(cartmain);
		
		for (CartsubVO item : cartsub) { // 장바구니에 있는 모든 상품들을 insert
			OrdersubVO ordersub = new OrdersubVO();
			ordersub.setOm_code(ordermain.getOm_code());
			ordersub.setP_code(item.getP_code());
			ordersub.setOs_cnt(item.getCs_cnt());
			ordermapper.insertsub(ordersub);
		}
		cartmapper.deleteSuball(cartmain); //장바구니 상세 삭제(cm_code 활용)
		cartmapper.deleteMain(cartmain); //장바구니 상세 삭제(cm_code 활용)
		
		return ordermain;// ordermain을 리턴할거야
	}	
	
	@Override
	public List<OrderdetailDTO> getListOrderDetail(OrdermainVO ordermain) {
		return ordermapper.getListOrderDetail(ordermain);
	}
	
	@Override
	public OrdermemberDTO getOrderTotal(OrdermainVO ordermain) {
		return ordermapper.getOrderTotal(ordermain);
	}
	
	
	
}
