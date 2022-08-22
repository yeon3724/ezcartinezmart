package com.ezmart.ezmart_mobile_app;

import com.android.volley.RequestQueue;

public class AppHelper {

    // 전체 엑티비티에서 사용할 아이디 값, URL 주소, 어댑터에서 사용할 RequestQueue를 전역 변수로 저장하는 클래스
    public static RequestQueue requestQueue;
    public static String id;
    public static String url_QR = "https://chart.googleapis.com/chart?cht=qr&chs=500x500&chl=i";
    public static String url_basketlist = "http://172.30.1.33:8083/web/basketlist.do";
    public static String url_buylist = "http://172.30.1.33:8083/web/buylist.do";
    public static String url_sendLogin = "http://172.30.1.33:8083/web/sendLogin.do";
    public static String url_cateList = "http://172.30.1.33:8083/web/cateList.do";
    public static String url_productSearch = "http://172.30.1.33:8083/web/productSearch.do";
    public static String url_Join = "http://172.30.1.33:8083/web/Join.do";

}
