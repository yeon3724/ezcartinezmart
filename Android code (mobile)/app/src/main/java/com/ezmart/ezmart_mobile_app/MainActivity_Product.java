package com.ezmart.ezmart_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity_Product extends AppCompatActivity {

    ListView product_list;

    RequestQueue requestQueue;
    StringRequest request;

    Adapter_Product productAdapter;
    ArrayList<Adapter_ProductVO> productDataset;

    // 뒤로가기 버튼을 누르면 메인 화면으로 돌아가기
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product);

        // 리스트뷰 연결
        product_list = findViewById(R.id.product_list);

        // 어댑터 연결을 위한 adapter 클래스와 dataset ArrayList 만들기
        productDataset = new ArrayList<>();
        productAdapter = new Adapter_Product(MainActivity_Product.this, R.layout.item_product, productDataset);

        // 통신에 사용할 requestQueue 불러오기 : 현재 엑티비티의 Context를 requestQueue에 대입
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // intent와 함께 담겨온 검색 값 받기
        Intent intent = getIntent();
        String search_txt = intent.getStringExtra("search_txt");
        String p_sort = intent.getStringExtra("p_sort");
        int num = Integer.parseInt(p_sort);

        // 검색어를 보내면 해당되는 상품 리스트를 돌려주는 API 서버의 url 주소
        String url = AppHelper.url_cateList;
        String url_search = AppHelper.url_productSearch;

        if(num==1) {
            url = url+"?p_sort="+p_sort;
        } else if(num==2) {
            url = url+"?p_sort="+p_sort;
        } else if(num==3){
            url = url+"?p_sort="+p_sort;
        } else if(num==4){
            url = url+"?p_sort="+p_sort;
        } else if(num==5){
            url = url+"?p_sort="+p_sort;
        } else if(num==6){
            url = url+"?p_sort="+p_sort;
        } else if(num==7){
            url = url+"?p_sort="+p_sort;
        } else if(num==8){
            url = url+"?p_sort="+p_sort;
        } else if(num==9){
            url = url+"?p_sort="+p_sort;
        } else if(num==10){
            url = url+"?p_sort="+p_sort;
        } else if(num==11){
            url = url+"?p_sort="+p_sort;
        } else if(num==12){
            url = url+"?p_sort="+p_sort;
        } else if(num==13){
            url = url+"?p_sort="+p_sort;
        } else if(num==14){
            url = url+"?p_sort="+p_sort;
        }else{
            url = url_search+"?search="+search_txt;
        }

        // 통신을 위해 request를 작성
        request = new StringRequest(
                Request.Method.GET, // GET 방식
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.d("developer", "상품리스트 데이터 받기 성공"); // 디버그
                            Toast.makeText(getApplicationContext(), "데이터 받기 성공", Toast.LENGTH_SHORT).show(); // 디버그
                            try {
                                JSONArray list = new JSONArray(response);
                                Log.d("developer",list.toString()); // 디버그
                                for (int i = 0; i < list.length(); i++) {
                                    JSONObject vo = (JSONObject) list.get(i);
                                    int product_seq = vo.getInt("p_seq"); // 상품 식별자
                                    int product_sort = vo.getInt("p_sort"); // 상품 정렬
                                    String product_cate = vo.getString("p_cate"); // 상품 분류
                                    String product_barcode = vo.getString("p_barcode"); // 바코드 번호
                                    String product_name = vo.getString("p_name"); // 상품명
                                    int product_price = vo.getInt("p_price"); // 상품 가격
                                    String product_image = vo.getString("p_image"); // 상품 이미지 URL
                                    productDataset.add(new Adapter_ProductVO(product_seq, product_sort, product_cate, product_barcode, product_name, product_price, product_image));
                                }
                                product_list.setAdapter(productAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d("developer", "상품리스트 데이터 받기 실패"); // 디버그
                            Toast.makeText(getApplicationContext(), "데이터 받기 실패", Toast.LENGTH_SHORT).show(); // 디버그
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("developer", "상품리스트 통신 실패"); // 디버그
                        Toast.makeText(getApplicationContext(), "통신 실패", Toast.LENGTH_SHORT).show(); // 디버그
                    }
                }
        );

        String requestdebug = requestQueue.add(request).toString();
        Log.d("developer", requestdebug);
        productDataset.clear();

    }
}