package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveVO {
	
	private String mb_id;
	private String p_name;
	private int p_price;
	private String p_image;
	private String p_seq;

}
