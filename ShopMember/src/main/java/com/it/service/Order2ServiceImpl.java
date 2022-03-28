package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.Cartmain2VO;
import com.it.domain.Cartsub2VO;
import com.it.domain.OrderdetailDTO;
import com.it.domain.Ordermain2VO;
import com.it.domain.OrdermemberDTO;
import com.it.domain.Ordersub2VO;
import com.it.mapper.Cart2Mapper;
import com.it.mapper.Order2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class Order2ServiceImpl implements Order2Service {
	
	@Setter(onMethod_ = @Autowired)
	private Order2Mapper ordermapper;
	
	@Setter(onMethod_ = @Autowired)
	private Cart2Mapper cartmapper;
	
	@Override
	public Ordermain2VO orderproc(Cartmain2VO cartmain) { // om_code 반환(주문리스트에서 사용하기 위함)
		// 주문main insert 신규 생성
		// cm_code를 사용하여 장바구니 sub 조회
		// 주문main insert
		// om_code 조회
		// 주문sub insert
		// 장바구니sub 삭제(cm_code 활용)
		// 장바구니main 삭제(cm_code 활용)
		// om_code를 반환
		
		Ordermain2VO ordermain = new Ordermain2VO();
		ordermain.setM_id(cartmain.getM_id());
		
		ordermapper.insertmain(ordermain);
		ordermain = ordermapper.readmainid(ordermain); // 역순으로 조회하여 가장 큰 om_code
		
		List<Cartsub2VO> cartsub = cartmapper.getListCart(cartmain);
		
		for (Cartsub2VO item : cartsub) { // 장바구니에 있는 모든 상품들을 insert
			Ordersub2VO ordersub = new Ordersub2VO();
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
	public List<OrderdetailDTO> getListOrderDetail(Ordermain2VO ordermain) {
		return ordermapper.getListOrderDetail(ordermain);
	}
	
	@Override
	public Ordermain2VO readmainid(Ordermain2VO ordermain) {
		return ordermapper.readmainid(ordermain);
	}
	
	@Override
	public OrdermemberDTO getOrderTotal(Ordermain2VO ordermain) {
		return ordermapper.getOrderTotal(ordermain);
	}
}
