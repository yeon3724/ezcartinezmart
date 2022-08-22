package com.ezmart.ezmart_mobile_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class MainActivity_Login extends AppCompatActivity {

    // 로그인 화면
    // 1. 회원가입 버튼을 누르면 회원가입 화면으로 이동한다.
    // 2. 로그인 버튼을 누르면, Oracle 서버와 통신하여 로그인 정보를 확인, 승인되면 메인 화면으로 이동한다.
    // 3. 자동로그인 체크박스에 체크하고, 로그인에 성공하면, 앱 내의 저장 공간에 해당 id를 저장하고 이후 자동으로 로그인 한다.

    EditText id_input_etxt, pw_input_etxt; // 아이디와 비밀번호를 입력할 EditText을 각각 선언
    Button login_btn; // 로그인 실행 버튼
    Button sign_btn; // 회원가입 화면 이동 버튼
    CheckBox auto_login_cbox; // 자동로그인 체크박스
    RequestQueue requestQueue; // RequestQueue 객체 선언
    StringRequest request; // StringRequest 객체 선언
    Boolean auto_login_check = false; // 자동로그인 체크 값을 저장하기 위한 boolean 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        // * 자동로그인 기능
        // - 앱이 시작되면, 일단 자동로그인 정보를 확인한다.
        // - 앱 내에 회원정보(id)가 저장되어 있으면, 로그인 화면에서 바로 메인 화면으로 넘어간다.
        // - 앱 내에 회원정보(id)가 저장되어 있지 않으면, 로그인 화면에 그대로 머문다.

        // 자동로그인 시 앱 내에 회원 정보(id)를 저장하기 위해 SharedPreferences를 사용
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit(); // 데이터를 저장하기 위해 사용하는 editor 메소드
        // 문자열 id 변수를 선언하고, 키 값 "user_id"로 저장된 회원정보(id)가 있으면 가져오고, 없으면 null 값을 대입한다.
        String id = prefs.getString("user_id", null);

        if (id != null) { // 문자열 id 변수가 null이 아니면,
            AppHelper.id = id; // 앱 내에서 공용으로 사용하기 위해 static 변수에 id 값을 저장한다.
            Toast.makeText(getApplicationContext(), "자동로그인(" + id + ")", Toast.LENGTH_SHORT).show(); // 디버그
            // 메인 페이지로 바로 넘어간다.
            Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
            startActivity(intent);
            finish();
        }

        // 위젯을 각각 연결한다.
        id_input_etxt = findViewById(R.id.id_input_etxt);
        pw_input_etxt = findViewById(R.id.pw_input_etxt);
        login_btn = findViewById(R.id.login_btn);
        sign_btn = findViewById(R.id.sign_btn);
        auto_login_cbox = findViewById(R.id.auto_login_cbox);
        // 현재 엑티비티의 Context를 requestQueue에 대입한다.
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        // 자동로그인 체크박스 체크 이벤트
        auto_login_cbox.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 체크박스를 체크하면 true, 체크하지 않으면 false가 된다.
                auto_login_check = auto_login_check ? false : true;
            }
        });

        // 회원가입 버튼 클릭이벤트
        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity_Sign.class);
                startActivity(intent);
                finish();
            }
        });

        // 로그인 버튼 클릭 이벤트
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 문자열 id, pw에 각각 입력값을 담는다.
                String id = id_input_etxt.getText().toString();
                String pw = pw_input_etxt.getText().toString();
                // 회원 아이디와 비밀번호를 보내면 로그인 승인 여부를 알려주는 API 서버의 url 주소
                String url = AppHelper.url_sendLogin;
                // 로그인 승인 여부를 HTTP 통신을 통해 돌려받기 위해 request를 작성한다.
                request = new StringRequest(
                        Request.Method.POST, // POST 방식
                        url, // "http://172.30.1.33:8083/web/sendLogin.do"
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // response로 돌려받은 값이 null이 아닐 경우
                                if (response != null) {
                                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show(); // 디버그
                                    AppHelper.id = id; // 앱 사용 중에 이용하기 위한 회원정보(id) 값을 AppHelper 객체에 static 변수로 저장
                                    // 자동로그인 체크박스 값이 true일 경우, "user_id" 키에 회원정보(id) 저장
                                    if (auto_login_check) {
                                        editor.putString("user_id", id);
                                        editor.apply();
                                    }
                                    // 메인 화면으로 이동
                                    Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show(); // 디버그
                                }
                            }
                        },
                        new Response.ErrorListener() { // response를 돌려받는데 실패했을 경우
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
                        return params;
                    }
                };

                String requestdebug = requestQueue.add(request).toString();
                Log.d("developer", requestdebug);

            }
        });

    }
}