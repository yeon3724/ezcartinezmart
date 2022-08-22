package com.ezmart.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezmart.domain.tbl_basketall;
import com.ezmart.domain.tbl_buy;
import com.ezmart.domain.tbl_buy_detail;
import com.ezmart.domain.tbl_buy_real;
import com.ezmart.domain.tbl_product;
import com.ezmart.mapper.DisplayMapper;
import com.ezmart.mapper.EzMartMapper;

@Controller
public class DisplayController {
	
	@Autowired
	DisplayMapper mapper;
	   
	   
	   
	@ResponseBody
	@RequestMapping("/DisplayList.do")
	public List<tbl_basketall> displaylist(Model model, String mb_id) {
		List<tbl_basketall> list = mapper.displaylist(mb_id);
		model.addAttribute("list", list);
		System.out.println(list.get(1).getB_amount());
		System.out.println(list);     
	
		return list;
	}
	 
	   
		// 구매관련 정보 받아오기
	@ResponseBody
	@RequestMapping("/getbuylist.do")
	public String getbuylist(String vo, String user_id) {
		
		System.out.println(vo);
		String[] vo_result = vo.split("VO\\{");
		int i = 1;
		int price = 0;
		String p_barcode = "";
		String p_cate = "";
		String p_check = "";
		String p_count = "";
		String p_image = "";
		String p_name = "" ;
		String p_price = "";
		String p_sort = "";
		ArrayList<String> pbarcode = new ArrayList<String>();
		ArrayList<String> pcount = new ArrayList<String>();
		for(String product : vo_result) {
			String[] p_names = vo_result[i].split(",");
			
			// 바코드
			String pre_barcode = p_names[0].replace("'", "");
			String[] result_barcode = pre_barcode.split("p_barcode=");
			p_barcode = result_barcode[1];
			pbarcode.add(p_barcode);
			// 카테고리
			String pre_cate = p_names[1].replaceAll("'", "");
			String[] result_cate = pre_cate.split("p_cate=");
			p_cate = result_cate[1];
			
			// 구매유무
			String pre_check = p_names[2].replaceAll("'", "");
			String[] result_check = pre_check.split("p_check=");
			p_check = result_check[1];
			
			// 상품구매수량
			String pre_count = p_names[3].replaceAll("'", "");
			String[] result_count = pre_count.split("p_count=");
			p_count = result_count[1];
			pcount.add(p_count);
			// 상품 이미지
			String pre_image = p_names[4].replaceAll("'", "");
			String[] result_image = pre_image.split("p_image=");
			p_image = result_image[1];
			
			// 상품명
			String pre_name = p_names[5].replaceAll("'", "");
			String[] result_name = pre_name.split("p_name=");
			p_name = result_name[1];
			// 상품가격
			String pre_price = p_names[6].replaceAll("'", "");
			String[] result_price = pre_price.split("p_price=");
			p_price = result_price[1];
			
			// 상품정렬번호
			String pre_sort = p_names[7].replaceAll("'", "");
			String[] result_sort = pre_sort.split("p_sort=");
			pre_sort = result_sort[1].replaceAll("\\}", "");
			p_sort = pre_sort.replace("\n", "");
			
			System.out.println("바코드 : "+p_barcode+" 카테고리 : "+p_cate+" 구매유무 : "+p_check+" 상품구매수량 : "+p_count+" 상품 이미지 : "+p_image+" 상품명 : "+p_name+" 상품가격 : "+p_price+" 상품정렬번호 : "+p_sort);
			
			price += Integer.parseInt(p_price)*Integer.parseInt(p_count);
			i++;
			if(i>=vo_result.length) {
				break;
			}
		}
		tbl_buy_real buy = new tbl_buy_real(0, price, "", user_id, 0, price, 1, "y");
		mapper.insertBuyInfo(buy);
		tbl_buy_real buyInfo = mapper.recodeBuy(buy);
		int cnt = 0;
		for(String cbarcode : pbarcode) {
			tbl_product productInfo = mapper.numInquire(cbarcode);
			tbl_buy_detail buy_detail = new tbl_buy_detail(0, buyInfo.getBuy_seq(), productInfo.getP_seq(), Integer.parseInt(pcount.get(cnt)), "");
			mapper.insertDetail(buy_detail);
			cnt++;
		}
		
		System.out.println(buyInfo.getMb_id()+"  /  "+buyInfo.getBuy_seq());
		buy.setBuy_seq(buyInfo.getBuy_seq());
		mapper.updateBasketCheck(buy);
		System.out.println(buy);
		
		return "getbuylist";
	}
		
		
	// 구매목록 추가하기
	@ResponseBody
	@RequestMapping("/insertBuy.do")
	public String insertBuy(tbl_buy vo) {
		System.out.println(vo);
		mapper.insertBuy(vo);
			
		return "insertBuy";
	}
	

		
	   
	   
	 

}
