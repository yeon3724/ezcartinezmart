package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tbl_basket {
	
	private int b_seq;
	private int p_seq;
	private String b_date;
	private String mb_id;
	private String b_check;

}
