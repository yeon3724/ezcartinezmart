package com.ezmart.ezmart_module;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_StandByScreen extends AppCompatActivity {

    // 대기화면을 출력하다 QR을 인식하면 로그인 화면으로 전환된다.
    // 로그인 정보(ID)를 저장한다.
    // 로그인 체크는 라즈베리파이에서 필터링 하고, 파이어베이스 실시간 DB에 등록한다.
    // 모듈(태블릿)은 파이어베이스 실시간 DB의 값이 변동되면 반응하여 페이지를 이동한다.

    // 파이어베이스에 저장할 데이터 정보를 담을 변수 선언
    private DatabaseReference mDatabase;

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
        setContentView(R.layout.activity_stand_by_screen);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // SharedPreferences를 사용하여 모듈 이용 중 ID 정보를 앱 내에 저장
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // 파이어베이스 주소를 불러와서 변수에 담는다.
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // mDatabase.child("module20001").setValue(null);

        mDatabase.child("users").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User post;
                String name, id;

                if (dataSnapshot.getValue(User.class) != null) {

                    post = dataSnapshot.getValue(User.class);
                    Log.d("FireBaseData", "getData : " + post.toString());
                    String[] result = post.toString().split("'");
                    name = result[1];
                    id = result[3];

                    Toast.makeText(MainActivity_StandByScreen.this, "메인화면(" + name + "이 로그인 중)", Toast.LENGTH_SHORT).show();

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("user_name", name);
                    editor.putString("user_id", id);
                    editor.apply();

                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(MainActivity_StandByScreen.this, "대기화면", Toast.LENGTH_SHORT).show();
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