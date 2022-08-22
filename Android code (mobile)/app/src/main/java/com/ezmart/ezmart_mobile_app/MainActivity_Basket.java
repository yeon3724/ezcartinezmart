package com.ezmart.ezmart_mobile_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class MainActivity_Basket extends AppCompatActivity {

    ListView basket_list;

    RequestQueue requestQueue;
    StringRequest request;

    Adapter_Basket basketAdapter;
    ArrayList<Adapter_BasketVO> basketDataset;

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
        setContentView(R.layout.activity_main_basket);

        // 리스트뷰 연결
        basket_list = findViewById(R.id.basket_list);

        // 어댑터 연결을 위한 adapter 클래스와 dataset ArrayList 만들기
        basketDataset = new ArrayList<>();
        basketAdapter = new Adapter_Basket(MainActivity_Basket.this, R.layout.item_basket, basketDataset);

        // 통신에 사용할 requestQueue 불러오기 : 현재 엑티비티의 Context를 requestQueue에 대입
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = AppHelper.url_basketlist;

        request = new StringRequest(
                Request.Method.POST, // POST 방식
                url, // "http://172.30.1.33:8083/web/basketlist.do"
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.d("developer", "장바구니 데이터 받기 성공"); // 디버그
                            Toast.makeText(getApplicationContext(), "데이터 받기 성공", Toast.LENGTH_SHORT).show(); // 디버그
                            try {
                                JSONArray list = new JSONArray(response);
                                Log.d("developer", list.toString()); // 디버그
                                for (int i = 0; i < list.length(); i++) {
                                    JSONObject vo = (JSONObject) list.get(i);
                                    int basket_b_seq = vo.getInt("b_seq");
                                    String basket_data = vo.getString("b_date");
                                    String basket_check = vo.getString("b_check");
                                    int basket_p_seq = vo.getInt("p_seq");
                                    String basket_id = vo.getString("mb_id");
                                    String basket_name = vo.getString("p_name");
                                    int basket_price = vo.getInt("p_price");
                                    int basket_amount = vo.getInt("b_amount");
                                    String basket_image = vo.getString("p_image");
                                    basketDataset.add(new Adapter_BasketVO(basket_b_seq, basket_data, basket_check, basket_p_seq, basket_id, basket_name, basket_price, basket_amount, basket_image));
                                }
                                basket_list.setAdapter(basketAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d("developer", "장바구니 데이터 받기 실패"); // 디버그
                            Toast.makeText(getApplicationContext(), "데이터 받기 실패", Toast.LENGTH_SHORT).show(); // 디버그
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("developer", "장바구니 통신 실패"); // 디버그
                        Toast.makeText(getApplicationContext(), "통신 실패", Toast.LENGTH_SHORT).show(); // 디버그
                    }
                }

        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("mb_id", AppHelper.id);
                return params;
            }
        };

        String requestdebug = requestQueue.add(request).toString();
        Log.d("developer", requestdebug);
        basketDataset.clear();

    }
}