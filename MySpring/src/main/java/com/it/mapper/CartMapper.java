package com.it.mapper;

import java.util.List;

import com.it.domain.CartdetailDTO;
import com.it.domain.CartmainVO;
import com.it.domain.CartmemberDTO;
import com.it.domain.CartsubVO;

public interface CartMapper {
	// 카트메인의 데이터를 읽고 쓰는 기본적인 처리를 위한 매퍼이며, 카트 매퍼와 카트 서브가 비슷하니까 혼동되지 않도록 용어설정에 주의
	public List<CartmainVO> getListmain();
	
	public List<CartsubVO> getListsub();
	
	public List<CartsubVO> getListCart(CartmainVO cartmain);
	
	public List<CartdetailDTO> getListCartDetail(CartmainVO cartmain);
	
	public CartmemberDTO getCartTotal(CartmainVO cartmain); // cart 총 금액
	
	public CartmainVO readMain(CartmainVO cartmain);
	
	public CartmainVO readMainid(CartmainVO cartmain);
	
	public CartsubVO readSub(CartsubVO cartsub);
	
	public CartsubVO readSubproduct(CartsubVO cartsub);
		
	public void insertMain(CartmainVO cartmain);
	
	public void insertSub(CartsubVO cartsub);

	public void updateSub(CartsubVO cartsub);

	public void deleteMain(CartmainVO cartmain);

	public void deleteSub(CartsubVO cartsub);
}
