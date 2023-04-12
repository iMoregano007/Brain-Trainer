package com.example.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView resultTextV;
    Button backToQuiz;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        resultTextV = findViewById(R.id.resultTextV);
        backToQuiz = findViewById(R.id.backToQuiz);
        String yourScore = getIntent().getStringExtra("keyyourScore");
        String tScore = getIntent().getStringExtra("keytScore");
        int yourScoreInt = Integer.parseInt(yourScore), tScoreInt = Integer.parseInt(tScore);
        if(tScoreInt == 0){
            result = "You have not attempted the Quiz";
        } else {
            double scorePercentage = ((double) yourScoreInt / (double) tScoreInt) * 100.00;
            result = "Your Score is " + yourScore + " out of " + tScore + ", with accuracy of " + String.format("%.2f", scorePercentage) + "%";
        }
        resultTextV.setText(result);

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