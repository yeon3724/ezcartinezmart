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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity_History extends AppCompatActivity {

    ListView history_list;

    RequestQueue requestQueue;
    StringRequest request;

    Adapter_History historyAdapter;
    ArrayList<Adapter_HistoryVO> historyDataset;

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
        setContentView(R.layout.activity_main_history);

        // 리스트뷰 연결
        history_list = findViewById(R.id.history_list);

        // 어댑터 연결을 위한 adapter 클래스와 dataset ArrayList 만들기
        historyDataset = new ArrayList<>();
        historyAdapter = new Adapter_History(getApplicationContext(), R.layout.item_history, historyDataset);

        // 통신에 사용할 requestQueue 불러오기 : 현재 엑티비티의 Context를 requestQueue에 대입
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        String url = AppHelper.url_buylist;

        request = new StringRequest(
                Request.Method.POST, // POST 방식
                url, // "http://172.30.1.57:8083/web/buylist.do"
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response != null) {
                            Log.d("developer", "구매내역 데이터 받기 성공"); // 디버그
                            Toast.makeText(getApplicationContext(), "데이터 받기 성공", Toast.LENGTH_SHORT).show(); // 디버그
                            try {
                                JSONArray list = new JSONArray(response);
                                Log.d("developer", list.toString()); // 디버그
                                for (int i = 0; i < list.length(); i++) {
                                    JSONObject vo = (JSONObject) list.get(i);
                                    int history_bd_seq = vo.getInt("bd_seq");
                                    int history_p_seq = vo.getInt("p_seq");
                                    String history_name = vo.getString("p_name");
                                    String history_cate = vo.getString("p_cate");
                                    String history_img = vo.getString("p_image");
                                    int history_discount = vo.getInt("buy_discount");
                                    int history_total = vo.getInt("buy_total");
                                    int history_amount = vo.getInt("pay_amount");
                                    String history_method = vo.getString("pay_method");
                                    String history_date = vo.getString("buy_date");
                                    historyDataset.add(new Adapter_HistoryVO(history_bd_seq, history_p_seq, history_cate, history_name, history_amount, history_discount, history_total, history_date, history_method, history_img));
                                }
                                history_list.setAdapter(historyAdapter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.d("developer", "구매내역 데이터 받기 실패"); // 디버그
                            Toast.makeText(getApplicationContext(), "데이터 받기 실패", Toast.LENGTH_SHORT).show(); // 디버그
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("developer", "구매내역 통신 실패"); // 디버그
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
        historyDataset.clear();

    }
}