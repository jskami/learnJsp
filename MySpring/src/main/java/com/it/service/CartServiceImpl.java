package com.it.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.domain.CartmainVO;
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
		cm = mapper.readMainid(cartmain); // 세션 아이디를 인수로 조회하여 결과 반환, 있으면 레코드 1개가 존재할 것이고, 없으면 null이 반환 / 상품이 아닌 장바구니를 한 번이라도 담았다는 흔적을 찾기 위함이다.
		if (cm == null) { // cartmain에 해당사용자의 레코드 1개를 신규 생성해야 함
			mapper.insertMain(cartmain); // 상품과 관계없이 cartmain에 id하나를 담아야 한다.
			// cm_code가 생성되었으나 조회해야 알 수 있다. 조회 쿼리 필요함
			cm = mapper.readMainid(cartmain); // 해당 사용자로 신규 추가 후 조회, 몰랐던 cm_code를 이제 알 수 있게 되었다.
			cartsub.setCm_code(cm.getCm_code());  // 조회한 신규cm_code를 cartsub에 추가 
			mapper.insertSub(cartsub); // 이건 실패! 상품정보는 들어와있지만 27라인에서 조회한 cm이 없다. 그래서 28라인에서 cm을 cartsub에 담아준다.  
		} else { // 이미 최소 1개는 카트에 상품이 존재한다는 의미
			cartsub.setCm_code(cm.getCm_code());  // 조회한 신규cm_code를 cartsub에 추가
			CartsubVO cs = new CartsubVO(); // cs에는 모든 정보가 담겨있어야 해
			cs = mapper.readSubproduct(cartsub);
			if (cs == null) { // 논리 : 선택한 상품이 장바구니에 없다면 insert
				mapper.insertSub(cartsub);
			} else { // 존재하면 기존상품에 더해서 치환하라
				cs.setCs_cnt(cs.getCs_cnt() + cartsub.getCs_cnt()); // 기존에 담겨진 상품 + 신규로 담은 상품 
				mapper.updateSub(cs);
			}
		}
	}
	
}
