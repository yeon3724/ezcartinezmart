package com.ezmart.ezmart_module;

public class Adapter_BasketVO {

    private String p_barcode;
    private String p_cate;
    private int b_check;
    private int p_count;
    private String p_image;
    private String p_name;
    private int p_price;
    private int p_sort;

    public Adapter_BasketVO() {
    }

    public Adapter_BasketVO(String p_barcode, String p_cate, int b_check, int p_count, String p_image, String p_name, int p_price, int p_sort) {
        this.p_barcode = p_barcode;
        this.p_cate = p_cate;
        this.b_check = b_check;
        this.p_count = p_count;
        this.p_image = p_image;
        this.p_name = p_name;
        this.p_price = p_price;
        this.p_sort = p_sort;
    }

    public String getP_barcode() {
        return p_barcode;
    }

    public void setP_barcode(String p_barcode) {
        this.p_barcode = p_barcode;
    }

    public String getP_cate() {
        return p_cate;
    }

    public void setP_cate(String p_cate) {
        this.p_cate = p_cate;
    }

    public int getB_check() {
        return b_check;
    }

    public void setB_check(int b_check) {
        this.b_check = b_check;
    }

    public int getP_count() {
        return p_count;
    }

    public void setP_count(int p_count) {
        this.p_count = p_count;
    }

    public String getP_image() {
        return p_image;
    }

    public void setP_image(String p_image) {
        this.p_image = p_image;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public int getP_price() {
        return p_price;
    }

    public void setP_price(int p_price) {
        this.p_price = p_price;
    }

    public int getP_sort() {
        return p_sort;
    }

    public void setP_sort(int p_sort) {
        this.p_sort = p_sort;
    }

    @Override
    public String toString() {
        return "BasketVO{" +
                "p_barcode='" + p_barcode + '\'' +
                ", p_cate='" + p_cate + '\'' +
                ", b_check=" + b_check +
                ", p_count=" + p_count +
                ", p_image='" + p_image + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_price=" + p_price +
                ", p_sort=" + p_sort +
                '}';
    }

}
