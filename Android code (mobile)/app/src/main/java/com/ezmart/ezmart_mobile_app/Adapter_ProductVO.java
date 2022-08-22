package com.ezmart.ezmart_mobile_app;

public class Adapter_ProductVO {

    private int product_seq;
    private int product_sort;
    private String product_cate;
    private String product_barcode;
    private String product_name;
    private int product_price;
    private String product_image;

    public Adapter_ProductVO(int product_seq, int product_sort, String product_cate, String product_barcode, String product_name, int product_price, String product_image) {
        this.product_seq = product_seq;
        this.product_sort = product_sort;
        this.product_cate = product_cate;
        this.product_barcode = product_barcode;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_image = product_image;
    }

    public int getProduct_seq() {
        return product_seq;
    }

    public void setProduct_seq(int product_seq) {
        this.product_seq = product_seq;
    }

    public int getProduct_sort() {
        return product_sort;
    }

    public void setProduct_sort(int product_sort) {
        this.product_sort = product_sort;
    }

    public String getProduct_barcode() {
        return product_barcode;
    }

    public void setProduct_barcode(String product_barcode) {
        this.product_barcode = product_barcode;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_cate() {
        return product_cate;
    }

    public void setProduct_cate(String product_cate) {
        this.product_cate = product_cate;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

}
