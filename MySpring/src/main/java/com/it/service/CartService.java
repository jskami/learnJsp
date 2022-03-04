package com.it.service;

import java.util.List;

import com.it.domain.CartdetailDTO;
import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;

public interface CartService {
	
	public void cartinsert(CartmainVO cartmain, CartsubVO cartsub); // cartmain에 여기에 세션을 통해서 사용자 정보를 불러올거야
	
	public CartmainVO readMainid(CartmainVO cartmain);	
	
	public List<CartsubVO> getListCart(CartmainVO cartmain);
	
	public List<CartdetailDTO> getListCartDetail(CartmainVO cartmain);
	
	public CartmemberDTO getCartTotal(CartmainVO cartmain);
	
	public void updateSub(CartsubVO cartsub);
	
	public void deleteSub(CartsubVO cartsub);

	public void deleteSuball(CartmainVO cartmain);
	
}
