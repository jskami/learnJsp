package com.it.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.CartdetailDTO;
import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;
import com.it.mapper.CartMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CartServiceImpl implements CartService {
	
	@Setter(onMethod_ = @Autowired)
	private CartMapper mapper;
	
	@Override
	public void cartinsert(CartmainVO cartmain, CartsubVO cartsub) {
		CartmainVO cm = new CartmainVO();
		cm = mapper.readMainid(cartmain); //세션 아이디를 인수로 조회하여 결과 반환, 있으면 레코드1개, 없으면 null 
		if (cm == null) { //cartmain 에 해당사용자의 레코드 1개를 신규생성해야 함
			mapper.insertMain(cartmain);  //cartmain 에 레코드 추가
			//cm_code 가 생성되었으나 조회해야 알 수 있음
			cm = mapper.readMainid(cartmain); //해당사용자로 신규 추가 후 조회
			cartsub.setCm_code(cm.getCm_code());  //조회한 신규cm_code를 cartsub에 추가
			mapper.insertSub(cartsub);
		} else { //이미 최소 1개는 카트에 상품이 존재한다는 의미
			cartsub.setCm_code(cm.getCm_code());  //조회한 신규cm_code를 cartsub에 추가
			CartsubVO cs = new CartsubVO();
			cs = mapper.readSubproduct(cartsub);
			if (cs == null) { //선택한 상품이 장바구니에 없다면
				mapper.insertSub(cartsub);
			} else { //존재한다면 덧셈하여 치환
				cs.setCs_cnt(cs.getCs_cnt() + cartsub.getCs_cnt());  //기존 + 신규
				mapper.updateSub(cs);
			}
		}
	}
	
	@Override
	public CartmainVO readMainid(CartmainVO cartmain) {
		cartmain = mapper.readMainid(cartmain); //특정 사용자 아이디로 조회
		return cartmain;
	}
	
	@Override
	public List<CartsubVO> getListCart(CartmainVO cartmain) {
		return mapper.getListCart(cartmain);
	}
	
	@Override
	public List<CartdetailDTO> getListCartDetail(CartmainVO cartmain) {
		return mapper.getListCartDetail(cartmain);
	}
	
	@Override
	public CartmemberDTO getCartTotal(CartmainVO cartmain) {
		return mapper.getCartTotal(cartmain);
	}
	
	@Override
	public void updateSub(CartsubVO cartsub) {
		mapper.updateSub(cartsub);
	}
	
	@Override
	public void deleteSub(CartsubVO cartsub) {
		mapper.deleteSub(cartsub);
		CartmainVO cartmain = new CartmainVO();
		cartmain.setCm_code(cartsub.getCm_code());
		List<CartsubVO> tmp = mapper.getListCart(cartmain);
		//log.info(tmp.size());
		if (tmp.size() == 0) { //장바구니에 상품이 1개도 없음
			mapper.deleteMain(cartmain); //cartmain 삭제
		}
	}
	
	@Override
	public void deleteSuball(CartmainVO cartmain) {
		mapper.deleteSuball(cartmain);  //장바구니 상세 삭제(cm_code 활용)
		mapper.deleteMain(cartmain);    //장바구니 메인 삭제(cm_code 활용)
	}

}