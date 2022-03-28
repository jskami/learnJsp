package com.it.service;

import java.util.List;

import com.it.domain.CartdetailDTO;
import com.it.domain.Cartmain2VO;
import com.it.domain.CartmemberDTO;
import com.it.domain.Cartsub2VO;

public interface Cart2Service {

	public void cartinsert(Cartmain2VO cartmain, Cartsub2VO cartsub); // cartmain에 여기에 세션을 통해서 사용자 정보를 불러올거야
	
	public Cartmain2VO readMainid(Cartmain2VO cartmain);	
	
	public List<Cartsub2VO> getListCart(Cartmain2VO cartmain);
	
	public List<CartdetailDTO> getListCartDetail(Cartmain2VO cartmain);
	
	public CartmemberDTO getCartTotal(Cartmain2VO cartmain);
	
	public void updateSub(Cartsub2VO cartsub);
	
	public void deleteSub(Cartsub2VO cartsub);

	public void deleteSuball(Cartmain2VO cartmain);
}
