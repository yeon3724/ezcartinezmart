package com.ezmart.mapper;

import java.util.List;

import com.ezmart.domain.MemberVO;
import com.ezmart.domain.SaveVO;
import com.ezmart.domain.tbl_basket;
import com.ezmart.domain.tbl_basketall;
import com.ezmart.domain.tbl_buy;
import com.ezmart.domain.tbl_product;

public interface EzMartMapper {
	
	public int memberJoin(MemberVO vo);
	
	public MemberVO memberLogin(MemberVO vo);
	
	public List<tbl_product> productList();
	
	// 바코드로 상품정보 불러오기
	public List<tbl_product> getproduct(String p_barcode);
	
	// 검색
	public List<tbl_product> productSearch(String search);
	
	// 장바구니 추가하기
	public int insertbasket(tbl_basket vo);

	// 장바구니 리스트
	public List<tbl_basketall> basketlist(String mb_id);
	
	// 장바구니 삭제하기
	public int basketdelete(int b_seq);
	
	// 구매내역 리스트
	public List<tbl_buy> buylist(String mb_id);
	
	// 상품리스트 - 카테고리별
	public List<tbl_product> catelist(int p_sort);

	

	

	


}
