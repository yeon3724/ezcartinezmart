package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tbl_buy_real {
	private int buy_seq;
	private int buy_total;
	private String buy_date;
	private String mb_id;
	private int buy_discount;
	private int pay_amount;
	private int pay_method;
	private String pay_yn;
}
