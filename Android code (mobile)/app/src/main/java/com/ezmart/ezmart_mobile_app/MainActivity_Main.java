package com.ezmart.ezmart_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity_Main extends AppCompatActivity {

    EditText search_input_etxt; // 검색할 값을 담을 EditText
    ImageView search_btn; // 검색 버튼
    ImageView basket_btn; // 장바구니 버튼
    ImageView buyhistory_btn; // 구매내역 버튼
    ImageView qrcreate_btn; // QR 로그인 버튼
    ImageView logout_btn; // 로그아웃 버튼
    ImageView img_nongsan,img_meat,img_fish,img_coffee,img_bev,img_snack,img_naengdong,img_src,img_alcohol,img_bath,img_clean,img_wisaeng,img_kitchen,img_toy;

    String search_txt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        // 뷰 아이디를 각각 연결
        search_input_etxt = findViewById(R.id.search_input_etxt);
        search_btn = findViewById(R.id.search_btn);
        basket_btn = findViewById(R.id.basket_btn);
        buyhistory_btn = findViewById(R.id.buyhistory_btn);
        qrcreate_btn = findViewById(R.id.qrcreate_btn);
        logout_btn = findViewById(R.id.logout_btn);

        // 로그아웃 시 앱 내 데이터에 접근하여 회원정보(id)를 삭제하기 위한 SharedPreferences 객체
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();

        // 검색 버튼 클릭 이벤트

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 상품 리스트 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity_Product.class);
                // 검색 데이터를 intent에 함께 담아 다른 엑티비티로 전송
                search_txt = search_input_etxt.getText().toString();
                intent.putExtra("search_txt", search_txt);
                intent.putExtra("p_sort", "0");
                startActivity(intent);
                finish();
            }
        });

        img_nongsan = findViewById(R.id.img_nongsan);
        img_meat = findViewById(R.id.img_meat);
        img_fish = findViewById(R.id.img_fish);
        img_coffee = findViewById(R.id.img_coffee);
        img_bev = findViewById(R.id.img_bev);
        img_snack = findViewById(R.id.img_snack);
        img_naengdong = findViewById(R.id.img_naengdong);
        img_src = findViewById(R.id.img_src);
        img_alcohol = findViewById(R.id.img_alcohol);
        img_bath = findViewById(R.id.img_bath);
        img_clean = findViewById(R.id.img_clean);
        img_wisaeng = findViewById(R.id.img_wisaeng);
        img_kitchen = findViewById(R.id.img_kitchen);
        img_toy = findViewById(R.id.img_toy);

        img_nongsan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("search_txt", "");
                intent.putExtra("p_sort", "12");
                startActivity(intent);
                finish();
            }
        });
        img_fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_bev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("search_txt", "");
                intent.putExtra("p_sort", "8");
                startActivity(intent);
                finish();
            }
        });
        img_naengdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("search_txt", "");
                intent.putExtra("p_sort", "4");
                startActivity(intent);
                finish();
            }
        });
        img_src.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_alcohol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_bath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("search_txt", "");
                intent.putExtra("p_sort", "1");
                startActivity(intent);
                finish();
            }
        });
        img_clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_wisaeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });
        img_toy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity_Product.class);
                intent.putExtra("p_sort","0");
                startActivity(intent);
                finish();
            }
        });

        // 장바구니 버튼 클릭 이벤트
        basket_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 장바구니 리스트 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity_Basket.class);
                startActivity(intent);
                finish();
            }
        });

        // 구매내역 버튼 클릭 이벤트
        buyhistory_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 구매내역 리스트 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity_History.class);
                startActivity(intent);
                finish();
            }
        });

        // QR 로그인 버튼 클릭 이벤트
        qrcreate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // QR 생성 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity_QRcode.class);
                startActivity(intent);
                finish();
            }
        });

        // 로그아웃 버튼 클릭 이벤트
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // user_id 키에 담긴 값을 삭제
                editor.remove("user_id");
                editor.apply();
                // 로그인 화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity_Login.class);
                startActivity(intent);
                finish();
            }
        });

    }

}