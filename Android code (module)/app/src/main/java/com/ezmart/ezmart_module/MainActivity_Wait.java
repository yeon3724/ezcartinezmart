package com.ezmart.ezmart_module;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_Wait extends AppCompatActivity {

    Button goon_btn, cancel_btn;

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
        setContentView(R.layout.activity_main_wait);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        goon_btn = findViewById(R.id.goon_btn);
        cancel_btn = findViewById(R.id.cancel_btn);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        goon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
                startActivity(intent);
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = "대기중";
                String help = "대기";
                String result = location + "," + help + "," + "2";
                mDatabase.child("call").child("module20001").setValue(result);
                Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
                startActivity(intent);
                finish();
            }
        });

    }
}