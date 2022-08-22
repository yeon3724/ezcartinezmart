package com.ezmart.ezmart_mobile_app;

public class Adapter_HistoryVO {

    private int history_bd_seq;
    private int history_p_seq;
    private String history_cate;
    private String history_name;
    private int history_amount;
    private int history_discount;
    private int history_total;
    private String history_date;
    private String history_method;
    private String history_img;

    public Adapter_HistoryVO(int history_bd_seq, int history_p_seq, String history_cate, String history_name, int history_amount, int history_discount, int history_total, String history_date, String history_method, String history_img) {
        this.history_bd_seq = history_bd_seq;
        this.history_p_seq = history_p_seq;
        this.history_cate = history_cate;
        this.history_name = history_name;
        this.history_amount = history_amount;
        this.history_discount = history_discount;
        this.history_total = history_total;
        this.history_date = history_date;
        this.history_method = history_method;
        this.history_img = history_img;
    }

    public int getHistory_bd_seq() {
        return history_bd_seq;
    }

    public void setHistory_bd_seq(int history_bd_seq) {
        this.history_bd_seq = history_bd_seq;
    }

    public int getHistory_p_seq() {
        return history_p_seq;
    }

    public void setHistory_p_seq(int history_p_seq) {
        this.history_p_seq = history_p_seq;
    }

    public String getHistory_cate() {
        return history_cate;
    }

    public void setHistory_cate(String history_cate) {
        this.history_cate = history_cate;
    }

    public String getHistory_name() {
        return history_name;
    }

    public void setHistory_name(String history_name) {
        this.history_name = history_name;
    }

    public int getHistory_amount() {
        return history_amount;
    }

    public void setHistory_amount(int history_amount) {
        this.history_amount = history_amount;
    }

    public int getHistory_discount() {
        return history_discount;
    }

    public void setHistory_discount(int history_discount) {
        this.history_discount = history_discount;
    }

    public int getHistory_total() {
        return history_total;
    }

    public void setHistory_total(int history_total) {
        this.history_total = history_total;
    }

    public String getHistory_date() {
        return history_date;
    }

    public void setHistory_date(String history_date) {
        this.history_date = history_date;
    }

    public String getHistory_method() {
        return history_method;
    }

    public void setHistory_method(String history_method) {
        this.history_method = history_method;
    }

    public String getHistory_img() {
        return history_img;
    }

    public void setHistory_img(String history_img) {
        this.history_img = history_img;
    }

}