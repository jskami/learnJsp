package com.it.mapper;

import java.util.List;

import com.it.domain.CartdetailDTO;
import com.it.domain.Cartmain2VO;
import com.it.domain.CartmemberDTO;
import com.it.domain.Cartsub2VO;

public interface Cart2Mapper {
	
	public List<Cartmain2VO> getListmain();
	
	public List<Cartsub2VO> getListsub();
	
	public List<Cartsub2VO> getListCart(Cartmain2VO cartmain);
	
	public List<CartdetailDTO> getListCartDetail(Cartmain2VO cartmain); // cart 상세보기
	
	public CartmemberDTO getCartTotal(Cartmain2VO cartmain); // 멤버별 cart 총 금액
	
	public Cartmain2VO readMain(Cartmain2VO cartmain);
	
	public Cartmain2VO readMainid(Cartmain2VO cartmain);
	
	public Cartsub2VO readSub(Cartsub2VO cartsub);
	
	public Cartsub2VO readSubproduct(Cartsub2VO cartsub);
		
	public void insertMain(Cartmain2VO cartmain);
	
	public void insertSub(Cartsub2VO cartsub);

	public void updateSub(Cartsub2VO cartsub);

	public void deleteMain(Cartmain2VO cartmain);

	public void deleteSub(Cartsub2VO cartsub);
	
	public void deleteSuball(Cartmain2VO cartmain);
}
