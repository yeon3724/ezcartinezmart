package com.ezmart.ezmart_mobile_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity_Sign extends AppCompatActivity {

    // 회원가입 화면 : 회원 아이디, 비밀번호, 이름, 닉네임, 이메일, 휴대폰 번호, 카드번호를 입력
    // 입력 값을 받을 7가지 EditText과 입력 버튼을 각각 선언
    EditText id_sign_etxt;
    EditText pw_sign_etxt;
    EditText name_sign_etxt;
    EditText nick_sign_etxt;
    EditText email_sign_etxt;
    EditText phone_sign_etxt;
    EditText card_sign_etxt;
    Button join_btn;

    RequestQueue requestQueue; // RequestQueue 객체 선언
    StringRequest request;  // StringRequest 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign);

        // 이미지 뷰를 각각 연결한다.
        id_sign_etxt = findViewById(R.id.id_sign_etxt);
        pw_sign_etxt = findViewById(R.id.pw_sign_etxt);
        name_sign_etxt = findViewById(R.id.name_sign_etxt);
        nick_sign_etxt = findViewById(R.id.nick_sign_etxt);
        email_sign_etxt = findViewById(R.id.email_sign_etxt);
        phone_sign_etxt = findViewById(R.id.phone_sign_etxt);
        card_sign_etxt = findViewById(R.id.card_sign_etxt);
        join_btn = findViewById(R.id.join_btn);

        // 통신을 위해 requestQueue에 해당 Context 대입
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 문자열 id, pw, name, nick, email, phone, card에 각각 입력값을 담는다.
                String id = id_sign_etxt.getText().toString();
                String pw = pw_sign_etxt.getText().toString();
                String name = name_sign_etxt.getText().toString();
                String nick = nick_sign_etxt.getText().toString();
                String email = email_sign_etxt.getText().toString();
                String phone = phone_sign_etxt.getText().toString();
                String card = card_sign_etxt.getText().toString();
                // 회원 가입 정보를 보내면 가입 성공 여부를 돌려주는 API 서버의 url 주소
                String url = AppHelper.url_Join;
                // 회원가입 승인 여부를 HTTP 통신을 통해 돌려받기 위해 request를 작성한다.
                request = new StringRequest(
                        Request.Method.POST, // POST 방식
                        url, // "http://172.30.1.33:8083/web/Join.do"
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response로 돌려받은 값이 null이 아닐 경우
                                if (response != null) {
                                    Toast.makeText(getApplicationContext(), "회원가입 성공", Toast.LENGTH_SHORT).show(); // 디버그
                                    // 로그인 화면으로 이동
                                    Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입 실패", Toast.LENGTH_SHORT).show(); // 디버그
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "통신 실패", Toast.LENGTH_SHORT).show(); // 디버그
                            }
                        }
                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("mb_id", id);
                        params.put("mb_pw", pw);
                        params.put("mb_name", name);
                        params.put("mb_nick", nick);
                        params.put("mb_email", email);
                        params.put("mb_phone", phone);
                        params.put("mb_cardnum", card);
                        return params;
                    }
                };

                String requestdebug = requestQueue.add(request).toString();
                Log.d("developer", requestdebug);

            }
        });

    }
}