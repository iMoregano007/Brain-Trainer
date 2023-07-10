package com.example.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {

    public void bestScoreSetter(int bestScr,String totalScore){
        String best = bestScr+"/"+totalScore;
        urBestScore.setText(best);
    }
    public void resultGenerator(int speedRemarkP, int accuracyRemarkP){
        String finalSpeedResult = speedRemark[speedRemarkP], finalAccuracyResult= accuracyRemark[accuracyRemarkP]+" Accuracy - "+String.format("%.2f", scorePercentage) +"%";
        speedResultTextV.setText(finalSpeedResult);
        accuracyResultTextV.setText(finalAccuracyResult);

    }
    double scorePercentage;
    LinearLayout resultTextV;

    TextView speedResultTextV, accuracyResultTextV, resultUsername, urScore , urBestScore;
    int bestScore;
    Button backToQuiz, logoutBtn;
    String result, username, usernameSetText, urTScore, score;
    String[] speedRemark= new String[]{"You need to improve your Speed ","Your Speed was Good, but can be improved ","Your Speed was Impressive "};
    String[] accuracyRemark= new String[]{"You need to improve your Accuracy, ","Your Accuracy was Good, but can be improved, ","Your Accuracy was Impressive, "};;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        SharedPreferences preferences = getSharedPreferences("isLogin",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        username = preferences.getString("username","username");
        bestScore = preferences.getInt("urBestScore",0);
        urTScore = preferences.getString("totalScore","Na");
        logoutBtn = findViewById(R.id.logoutBtn);
        resultUsername =findViewById(R.id.resultUsername);
        speedResultTextV = findViewById(R.id.speedResultTextV);
        accuracyResultTextV = findViewById(R.id.accuracyResultTextV);
        resultTextV = findViewById(R.id.resultTextV);

        backToQuiz = findViewById(R.id.backToQuiz);
        urBestScore = findViewById(R.id.urBestScore);
        urScore = findViewById(R.id.urRecentScore);

        bestScoreSetter(bestScore,urTScore);
        usernameSetText = "Hey "+username+",";
        String yourScore = getIntent().getStringExtra("keyyourScore");
        String tScore = getIntent().getStringExtra("keytScore");
        int yourScoreInt = Integer.parseInt(yourScore), tScoreInt = Integer.parseInt(tScore);
        if(tScoreInt == 0){
            result = "You have not attempted the Quiz";
            speedResultTextV.setText(result);
            accuracyResultTextV.setText(result);

        } else {
            scorePercentage = ((double) yourScoreInt / (double) tScoreInt) * 100.00;
            if(yourScoreInt > bestScore){
                editor.putInt("urBestScore",yourScoreInt);
                editor.putString("totalScore", tScore);
                editor.apply();
                bestScoreSetter(yourScoreInt,tScore);
            }

            if(tScoreInt > 15){
                if(scorePercentage >90.00){
                    resultGenerator(2,2);
                } else if (scorePercentage > 70.00) {
                    resultGenerator(2,1);

                } else{
                    resultGenerator(2,0);
                }
            } else if (tScoreInt >10) {
                if(scorePercentage >90.00){
                    resultGenerator(1,2);
                } else if (scorePercentage > 70.00) {
                    resultGenerator(1,1);

                } else{
                    resultGenerator(1,0);
                }

            } else {
                if(scorePercentage >90.00){
                    resultGenerator(0,2);
                } else if (scorePercentage > 70.00) {
                    resultGenerator(0,1);

                } else{
                    resultGenerator(0,0);
                }
            }

        }
        resultUsername.setText(usernameSetText);
        score = yourScoreInt+"/"+tScoreInt;
        urScore.setText(score);

        resultTextV.setScaleX(0.1f);
        resultTextV.setScaleY(0.1f);

        resultTextV.animate().scaleX(1f).scaleY(1f).setDuration(1000);
        backToQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("flag",false);
                editor.putString("username","username");
                editor.putInt("urBestScore",0);
                editor.putString("totalScore","Na");
                editor.apply();
                startActivity(new Intent(ResultActivity.this,LoginActivity.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ResultActivity.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}