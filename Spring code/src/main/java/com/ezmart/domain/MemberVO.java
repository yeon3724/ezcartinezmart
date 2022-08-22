package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class MemberVO {
	
	@NonNull private String mb_id;
	@NonNull private String mb_pw;
	private String mb_name;
	private String mb_nick;
	private String mb_email;
	private String mb_phone;
	private int mb_type;
	private String mb_cardnum;
	

	
	
}
