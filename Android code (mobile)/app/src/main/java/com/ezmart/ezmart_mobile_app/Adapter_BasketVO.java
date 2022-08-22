package com.ezmart.ezmart_mobile_app;

public class Adapter_BasketVO {

    private int basket_b_seq;
    private String basket_data;
    private String basket_check;
    private int basket_p_seq;
    private String basket_id;
    private String basket_name;
    private int basket_price;
    private int basket_amount;
    private String basket_image;

    public Adapter_BasketVO(int basket_b_seq, String basket_data, String basket_check, int basket_p_seq, String basket_id, String basket_name, int basket_price, int basket_amount, String basket_image) {
        this.basket_b_seq = basket_b_seq;
        this.basket_data = basket_data;
        this.basket_check = basket_check;
        this.basket_p_seq = basket_p_seq;
        this.basket_id = basket_id;
        this.basket_name = basket_name;
        this.basket_price = basket_price;
        this.basket_amount = basket_amount;
        this.basket_image = basket_image;
    }

    public int getBasket_b_seq() {
        return basket_b_seq;
    }

    public void setBasket_b_seq(int basket_b_seq) {
        this.basket_b_seq = basket_b_seq;
    }

    public String getBasket_data() {
        return basket_data;
    }

    public void setBasket_data(String basket_data) {
        this.basket_data = basket_data;
    }

    public String getBasket_check() {
        return basket_check;
    }

    public void setBasket_check(String basket_check) {
        this.basket_check = basket_check;
    }

    public int getBasket_p_seq() {
        return basket_p_seq;
    }

    public void setBasket_p_seq(int basket_p_seq) {
        this.basket_p_seq = basket_p_seq;
    }

    public String getBasket_id() {
        return basket_id;
    }

    public void setBasket_id(String basket_id) {
        this.basket_id = basket_id;
    }

    public String getBasket_name() {
        return basket_name;
    }

    public void setBasket_name(String basket_name) {
        this.basket_name = basket_name;
    }

    public int getBasket_price() {
        return basket_price;
    }

    public void setBasket_price(int basket_price) {
        this.basket_price = basket_price;
    }

    public int getBasket_amount() {
        return basket_amount;
    }

    public void setBasket_amount(int basket_amount) {
        this.basket_amount = basket_amount;
    }

    public String getBasket_image() {
        return basket_image;
    }

    public void setBasket_image(String basket_image) {
        this.basket_image = basket_image;
    }

}
