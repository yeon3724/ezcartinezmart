package com.ezmart.ezmart_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity_Main extends AppCompatActivity {

    private DatabaseReference mDatabase; // 파이어베이스 데이터베이스 저장 객체 선언

    TextView user_txt, payall_txt;
    Button logout_btn, call_btn, pay_btn;
    ListView buy_list, ad_list;

    Adapter_Basket basketAdapter;
    ArrayList<Adapter_BasketVO> basketDataset;

    Adapter_Ad adAdapter;
    ArrayList<Adapter_AdVO> adDataset;

    RequestQueue requestQueue;
    StringRequest request;

    String user = "";

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        user_txt = findViewById(R.id.user_txt);
        payall_txt = findViewById(R.id.payall_txt);
        logout_btn = findViewById(R.id.logout_btn);
        pay_btn = findViewById(R.id.pay_btn);
        call_btn = findViewById(R.id.call_btn);
        buy_list = findViewById(R.id.buy_list);
        ad_list = findViewById(R.id.ad_list);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        user = prefs.getString("user_name", null);
        user_txt.setText(user + "님 EZ-MART에 오신 걸 환영합니다!");

        basketDataset = new ArrayList<>();
        basketAdapter = new Adapter_Basket(this, R.layout.item_buylist, basketDataset);

        adDataset = new ArrayList<>();
        adAdapter = new Adapter_Ad(this, R.layout.item_adlist, adDataset);

        mDatabase = FirebaseDatabase.getInstance().getReference(); // 저장 객체에 저장할 파이어베이스 실시간DB 주소

        readBasketList();

        getAd();

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewUser("1", null, null);
                Intent intent = new Intent(getApplicationContext(), MainActivity_StandByScreen.class);
                startActivity(intent);
                finish();
            }
        });

        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_Pay.class);
                startActivity(intent);
                finish();
            }
        });

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_Call.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getAd() {

        Log.d("FireBaseData", "into");

        mDatabase.child("ad").addValueEventListener(new ValueEventListener() {
            // 실시간DB에서 변동이 발생하면 이벤트가 이루어지는 곳
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adDataset.clear();
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    Log.d("FireBaseData", "getData : " + children.getValue(Adapter_AdVO.class));
                    Adapter_AdVO vo = children.getValue(Adapter_AdVO.class);
                    adDataset.add(vo);
                }
                // Get Post object and use the values to update the UI
                ad_list.setAdapter(adAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    private void writeNewUser(String userId, String name, String id) {
        User user = new User(name, id);

        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(MainActivity_Main.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(MainActivity_Main.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void readBasketList() {

        mDatabase.child("module20001").orderByChild("p_sort").addValueEventListener(new ValueEventListener() {
            // 실시간DB에서 변동이 발생하면 이벤트가 이루어지는 곳
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                basketDataset.clear();
                int payall = 0;
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    Adapter_BasketVO vo = children.getValue(Adapter_BasketVO.class);
                    Log.d("FireBaseData", "getData : " + vo.toString());
                    if(vo.getB_check()==1) {
                        payall += vo.getP_price() * vo.getP_count();
                    }
                    basketDataset.add(vo);
                }

                DecimalFormat myFormatter = new DecimalFormat("###,###");

                payall_txt.setText(myFormatter.format(payall) + " 원");

                // Get Post object and use the values to update the UI
                buy_list.setAdapter(basketAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }

        });

        mDatabase.child("ad").child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Ad post;

                if (dataSnapshot.getValue(Ad.class) != null) {

                    post = dataSnapshot.getValue(Ad.class);

                    if(post.getAd_onoff().equals("on")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity_Ad.class);
                        startActivity(intent);
                        finish();
                    }

                } else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }

        });

    }

}