package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button loginBtn = (Button) findViewById(R.id.logInBtn);
        EditText logInUsername = findViewById(R.id.logInUsername);
        SharedPreferences pref = getSharedPreferences("isLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        loginBtn.setEnabled(false);

        logInUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length()==0){
                    loginBtn.setEnabled(false);
                } else{
                    loginBtn.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = logInUsername.getText().toString();
                editor.putBoolean("flag",true);
                editor.putString("username",username);
                editor.apply();
                startActivity(new Intent(LoginActivity.this,PreviewActivity.class));
            }
        });

    }
}