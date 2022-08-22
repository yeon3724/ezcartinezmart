package com.ezmart.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezmart.domain.MemberVO;
import com.ezmart.domain.SaveVO;
import com.ezmart.domain.tbl_basket;
import com.ezmart.domain.tbl_basketall;
import com.ezmart.domain.tbl_buy;
import com.ezmart.domain.tbl_member;
import com.ezmart.domain.tbl_product;
import com.ezmart.mapper.EzMartMapper;
import org.springframework.ui.Model;

@Controller
public class EzMartController {
   
   @Autowired
   EzMartMapper mapper;
   
   
   // 1. 처음 실행 했을 때 이동하는 주소
   @RequestMapping("/main.do")
   public void main() {}
   
   
   // 1. 회원가입
   @RequestMapping("/Join.do")
   public @ResponseBody String memerJoin(MemberVO vo) {
      
      System.out.println(vo);
      
      mapper.memberJoin(vo);
      return "redirect:/main.do";
      
   }
   
   
//   @RequestMapping("/Login.do")
//   public String gologin() {
//      return "login";
//   }
//   
//   
//   // 2-1. 로그인
//    @PostMapping("/Login.do")
//    public String login(MemberVO vo, Model model) {
//        MemberVO result = mapper.memberLogin(vo);
//        String url = "";
//        model.addAttribute("vo", vo);
//        if (result != null) {
//          url = "redirect:/main.do";
//       } else {
//          url = "login";
//       }
//       
//       return url;
//    }
    
    
    // 2. 로그인 (+회원정보 안드로이드로 넘겨주기)
   @RequestMapping("/sendLogin.do")
   public @ResponseBody JSONObject sendLogin(MemberVO vo) {
      System.out.println(vo.getMb_id());
      JSONObject result = new JSONObject();
      JSONArray jarray = new JSONArray();
      MemberVO resultVO = mapper.memberLogin(vo);
      if(vo!=null) {
         JSONObject pre = new JSONObject();
         pre.put("mb_id", resultVO.getMb_id());
         pre.put("mb_pw", resultVO.getMb_pw());
         pre.put("mb_name", resultVO.getMb_name());
         pre.put("mb_nick", resultVO.getMb_nick());
         pre.put("mb_email", resultVO.getMb_email());
         pre.put("mb_phone", resultVO.getMb_phone());
         pre.put("mb_type", resultVO.getMb_type());
         pre.put("mb_cardnum", resultVO.getMb_cardnum());
         jarray.add(0, pre);
         result.put("user", jarray);
      }
      return result;
   }
    
    
    // 3. 상품목록 불러오기
    @ResponseBody
    @RequestMapping("/ProductList.do")
    public Map<String, Object> productList(){
       Map<String, Object> result = new HashMap<String, Object>();
       result.put("datas", mapper.productList());
       return result;
    }
    
	// 3-1. 상품목록 - 욕실용품
	@ResponseBody
	@RequestMapping("/cateList.do")
	public List<tbl_product> catelist(int p_sort) {
		List<tbl_product> list = mapper.catelist(p_sort);
		System.out.println(list);
		
		return list;
	}
    
    // 4. 상품정보 바코드로 불러오기
    @ResponseBody
    @RequestMapping("/getproduct.do")
    public Map<String, Object> getproduct(String p_barcode){
       Map<String, Object> result = new HashMap<String, Object>();
       result.put("datas", mapper.getproduct(p_barcode));
       
       System.out.println(result);
       
       return result;
    }
    
    
    // 5. 상품목록 검색
    @ResponseBody
    @RequestMapping("/productSearch.do")
    public Object productSearch(String search){
   
         return mapper.productSearch("%"+search+"%");
       
    }
  
    
    // 6-1. 장바구니에 상품 담기
    @ResponseBody
    @RequestMapping("/insertbasket.do")
    public String insertbasket(tbl_basket vo) {
       
    	System.out.println(vo);
       mapper.insertbasket(vo);
       return "insertbasket";
       
       
    }
   

   // 6-2. 장바구니 리스트 
    @ResponseBody
    @RequestMapping("/basketlist.do")
    public List<tbl_basketall> basketlist(Model model, String mb_id) {
       List<tbl_basketall> list = mapper.basketlist(mb_id);
       model.addAttribute("list", list);
       System.out.println(list);     

       return list;
    }
 
    // 6-3. 장바구니 상품 삭제하기
    @ResponseBody
    @RequestMapping("/basketdelete.do")
    public String delete(int b_seq) {
    	mapper.basketdelete(b_seq);
    	System.out.println(b_seq);
    	return "basketlist";
    }
    
    
    // 7. 구매내역 리스트
    @ResponseBody
    @RequestMapping("/buylist.do")
    public List<tbl_buy> buylist(Model model, String mb_id) {
    	List<tbl_buy> list = mapper.buylist(mb_id);
    	model.addAttribute("list", list);
    	System.out.println(list);
    	
    	return list;
    }
    
    
    
    
}