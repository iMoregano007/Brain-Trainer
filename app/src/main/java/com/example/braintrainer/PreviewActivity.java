package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Button startBtn = findViewById(R.id.startBtn);
        TextView previewWelcomeText = findViewById(R.id.previewWelcomeText);
        String welcomeText , userName;
        SharedPreferences pref = getSharedPreferences("isLogin", MODE_PRIVATE);
        userName = pref.getString("username","username");
        welcomeText = "Hello "+userName+", Are you ready to be smarter than yesterday?";
        previewWelcomeText.setText(welcomeText);
        previewWelcomeText.setScaleX(0.1f);
        previewWelcomeText.setScaleY(0.1f);
        previewWelcomeText.animate().scaleX(1f).scaleY(1f).setDuration(1000);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreviewActivity.this,MainActivity.class));
            }
        });
    }
}