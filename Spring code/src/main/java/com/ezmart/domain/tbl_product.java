package com.ezmart.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class tbl_product {
	
	private int p_seq;
	private String p_name;
	private String p_barcode;
	private int p_price;
	private String p_image;
	private String p_cate;
	private int p_sort;

}
