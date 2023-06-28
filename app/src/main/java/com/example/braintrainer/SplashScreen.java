package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences pref = getSharedPreferences("isLogin",MODE_PRIVATE);
        Boolean isLogIn = pref.getBoolean("flag",false);
        String username = pref.getString("username","username");

        new CountDownTimer(5000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent;
                if (isLogIn){
                    intent = new Intent(SplashScreen.this,PreviewActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);

                }
            }
        }.start();
    }
}