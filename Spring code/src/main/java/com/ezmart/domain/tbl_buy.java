package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class tbl_buy {
	
	private int bd_seq;
    private int p_seq;
    private String p_name;
    private String p_cate;
    private String p_image;
    private int buy_discount;
    private int buy_total;
    private int pay_amount;
    private String pay_method;
    private String buy_date;
    private String mb_id;
	

}
