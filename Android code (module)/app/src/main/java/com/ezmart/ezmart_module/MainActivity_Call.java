package com.ezmart.ezmart_module;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity_Call extends AppCompatActivity {

    Button submit_btn, back_btn;
    RadioGroup location_rgroup, help_rgroup;
    RadioButton location_btn01, location_btn02, location_btn03, location_btn04, location_btn05, location_btn06, location_btn07, location_btn08, location_btn09;
    RadioButton help_btn01, help_btn02, help_btn03, help_btn04, help_btn05, help_btn06;

    String location = "주류코너";
    String help = "매장안내";

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
        setContentView(R.layout.activity_main_call);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        submit_btn = findViewById(R.id.submit_btn);
        back_btn = findViewById(R.id.back_btn);
        location_rgroup = findViewById(R.id.location_rgroup);
        location_btn01 = findViewById(R.id.location_btn01);
        location_btn02 = findViewById(R.id.location_btn02);
        location_btn03 = findViewById(R.id.location_btn03);
        location_btn04 = findViewById(R.id.location_btn04);
        location_btn05 = findViewById(R.id.location_btn05);
        location_btn06 = findViewById(R.id.location_btn06);
        location_btn07 = findViewById(R.id.location_btn07);
        location_btn08 = findViewById(R.id.location_btn08);
        location_btn09 = findViewById(R.id.location_btn09);
        help_rgroup = findViewById(R.id.help_rgroup);
        help_btn01 = findViewById(R.id.help_btn01);
        help_btn02 = findViewById(R.id.help_btn02);
        help_btn03 = findViewById(R.id.help_btn03);
        help_btn04 = findViewById(R.id.help_btn04);
        help_btn05 = findViewById(R.id.help_btn05);
        help_btn06 = findViewById(R.id.help_btn06);

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        location_btn01.setChecked(true);
        help_btn01.setChecked(true);

        location_btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "주류코너";
            }
        });

        location_btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "가공코너";
            }
        });

        location_btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "축산코너";
            }
        });

        location_btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "음료코너";
            }
        });

        location_btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "수산코너";
            }
        });

        location_btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "완구코너";
            }
        });

        location_btn07.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "과자코너";
            }
        });

        location_btn08.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "생활코너";
            }
        });

        location_btn09.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                location = "과일코너";
            }
        });

        help_btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help = "매장안내";
            }
        });

        help_btn02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help = "기기문의";
            }
        });

        help_btn03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help = "고장문의";
            }
        });

        help_btn04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help = "미아신고";
            }
        });

        help_btn05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help = "동물신고";
            }
        });

        help_btn06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                help = "기타문의";
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = location + "," + help + "," + "1";
                mDatabase.child("call").child("module20001").setValue(result);
                Intent intent = new Intent(getApplicationContext(), MainActivity_Wait.class);
                startActivity(intent);
                finish();
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity_Main.class);
                startActivity(intent);
                finish();
            }
        });

    }
}