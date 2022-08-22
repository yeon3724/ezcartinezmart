package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//혬VO

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tbl_member {
	private String mb_id;
	private String mb_pw;
	private String mb_name;
	private String mb_nick;
	private String mb_email;
	private String mb_phone;
	private int mb_type;
	private String mb_cardnum;
}
