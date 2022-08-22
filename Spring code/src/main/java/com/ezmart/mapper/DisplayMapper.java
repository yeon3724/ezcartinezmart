package com.ezmart.mapper;

import java.util.List;

import com.ezmart.domain.tbl_basket;
import com.ezmart.domain.tbl_basketall;
import com.ezmart.domain.tbl_buy;
import com.ezmart.domain.tbl_buy_detail;
import com.ezmart.domain.tbl_buy_real;
import com.ezmart.domain.tbl_product;

public interface DisplayMapper {

	public List<tbl_basketall> displaylist(String mb_id);
	
	// 구매목록 추가하기
	public int insertBuy(tbl_buy vo);
	
	// 구매 목록 최근 이력 데이터 확인
	public tbl_buy_real recodeBuy(tbl_buy_real buy);
	
	// 상품 번호 조회 확인
	public tbl_product numInquire(String p_barcode);
	
	// 상품 디테일 DB 삽입
	public int insertDetail(tbl_buy_detail detail);
	
	// 구매 정보 DB 삽입
	public int insertBuyInfo(tbl_buy_real buy);
	
	// 결제한 상품 장바구니 목록에서 구매(b_check = 1)로 상태 변경하기
	public void updateBasketCheck(tbl_buy_real buy);
	
	
	
}
