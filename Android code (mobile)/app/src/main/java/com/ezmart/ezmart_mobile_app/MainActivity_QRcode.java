package com.ezmart.ezmart_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity_QRcode extends AppCompatActivity {

    TextView end_txt, timer_txt;
    ImageView qrcode_img;
    Button create_qr_btn;

    CountDownTimer cdt;

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
        setContentView(R.layout.activity_main_qrcode);

        end_txt = findViewById(R.id.end_txt);
        timer_txt = findViewById(R.id.timer_txt);
        qrcode_img = findViewById(R.id.qrcode_img);
        create_qr_btn = findViewById(R.id.create_qr_btn);

        sendImageRequest();
        countdown();

        end_txt.setVisibility(View.INVISIBLE);
        create_qr_btn.setVisibility(View.INVISIBLE);

        create_qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendImageRequest();
                cdt.cancel();
                countdown();

                qrcode_img.setVisibility(View.VISIBLE);
                timer_txt.setVisibility(View.VISIBLE);
                end_txt.setVisibility(View.INVISIBLE);
                create_qr_btn.setVisibility(View.INVISIBLE);

            }
        });
    }

    public void sendImageRequest() {

        String id = AppHelper.id;

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String dummy = generatedString;

        String url = AppHelper.url_QR + id + "^" + dummy;

        ImageLoadTask task = new ImageLoadTask(url, qrcode_img);
        task.execute();

    }

    public void countdown() {

        cdt = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer_txt.setText("남은 유효 시간 : " + millisUntilFinished / 1000 + "초");
            }

            public void onFinish() {
                qrcode_img.setVisibility(View.INVISIBLE);
                timer_txt.setVisibility(View.INVISIBLE);
                end_txt.setVisibility(View.VISIBLE);
                create_qr_btn.setVisibility(View.VISIBLE);
            }

        }.start();

    }

}