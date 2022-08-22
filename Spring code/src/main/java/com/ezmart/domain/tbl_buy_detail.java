package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class tbl_buy_detail {
	
	private int bd_seq;
	private int buy_seq;
	private int p_seq;
	private int bd_cnt;
	private String bd_date;
	

}
