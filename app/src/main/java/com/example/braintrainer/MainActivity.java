package com.example.braintrainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int max = 450, min = 10;
    int num1 , num2, num3,num4, counter, problemCounter, optionT;
    Button  option1,option2,option3,option4, finishBtn;
    TextView timerTextV, scoreTextV, totalScore, sumProblem;
    GridLayout gridLayout;
    ConstraintLayout constraintLayout;
    CountDownTimer countDownTimer;
    public void sumProblemGenerator(){

        problemCounter = (int)(Math.random()*(4-1+1)+1);
        if(problemCounter == 1) {
            int x = (int) (Math.random() * (max - min + 1) + min), y = (int) (Math.random() * (max - min + 1) + min);
            num1 = x + y;
            num2 = num1 - 80;
            num3 = num1 +50;
            num4 = num1 + 100;
            sumProblem.setText(Integer.toString(x) + " + " + Integer.toString(y));
        } else if(problemCounter == 2){
            int x = (int) (Math.random() * (600 - 300 + 1) + 300), y = (int) (Math.random() * (250 - 150 + 1) + 150);
            num1 = x - y;
            num2 = num1 - 30;
            num3 = num1 +50;
            num4 = num1 - 100;
            sumProblem.setText(Integer.toString(x) + " - " + Integer.toString(y));
        } else if(problemCounter == 3){
            int x = (int) (Math.random() * (55 - min + 1) + min), y = (int) (Math.random() * (55 - min + 1) + min);
            num1 = x * y;
            num2 = num1 - y;
            num3 = num1 + x;
            num4 = num1 + 2*x ;
            sumProblem.setText(Integer.toString(x) + " * " + Integer.toString(y));
        } else {
            int x = (int) (Math.random() * (25 - min + 1) + min), y = (int) (Math.random() * (20 - min + 1) + min);
            int temp = x *y;
            num1 = x ;
            x = temp;
            num2 = num1 - (int)(Math.random()*(4-1+1)+1);
            num3 = num1 + (int)(Math.random()*(4-1+1)+1);
            num4 = num1 + (int)(Math.random()*(4-1+1)+1);
            sumProblem.setText(Integer.toString(x) + " / " + Integer.toString(y));
        }
        counter = (int)(Math.random()*(4-1+1)+1);
        switch(counter){
            case 1:
                optionSetter(num1,num2,num3,num4);
                break;
            case 2:
                optionSetter(num4,num2,num1,num3);
                break;
            case 3:
                optionSetter(num3,num1,num2,num4);
                break;
            case 4:
                optionSetter(num4,num3,num2,num1);
                break;


        }
        showAnimation();


    }
    public void optionSetter(int num1,int num2 , int num3, int num4){

        option1.setText(Integer.toString(num1));
        option2.setText(Integer.toString(num2));
        option3.setText(Integer.toString(num3));
        option4.setText(Integer.toString(num4));



    }

    public void comparisonFunc(int optionT){
        int urScore= Integer.parseInt(scoreTextV.getText().toString());
        int totalS = Integer.parseInt(totalScore.getText().toString());
        if(optionT==num1){
            urScore++;
            totalS++;
            scoreTextV.setText(Integer.toString(urScore));
            totalScore.setText(Integer.toString(totalS));
        } else {
            totalS++;
            totalScore.setText(Integer.toString(totalS));
        }
    }

    public void resultGenerator(){
        String yourScore = scoreTextV.getText().toString();
        String tScore = totalScore.getText().toString();
        Intent intent = new Intent(MainActivity.this,ResultActivity.class);
        intent.putExtra("keyyourScore",yourScore);
        intent.putExtra("keytScore",tScore);
        startActivity(intent);

    }

    public void animationPartOne(){
        constraintLayout.setTranslationX(1000);
        timerTextV.setTranslationX(-1000);

        sumProblem.setAlpha(0);

        gridLayout.setAlpha(0);
        gridLayout.setScaleX(0.1f);
        gridLayout.setScaleY(0.1f);
    }
    public void showAnimation(){
        timerTextV.animate().translationX(0).setDuration(1000);
        constraintLayout.animate().translationX(0).setDuration(1000);
        gridLayout.animate().alpha(1).scaleX(1f).scaleY(1f).setDuration(1000);

        sumProblem.animate().alpha(1).rotation(720).setDuration(1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        finishBtn = findViewById(R.id.finishBtn);
        option1 = findViewById(R.id.option1);
        option1.setOnClickListener(this);
        option2 = findViewById(R.id.option2);
        option2.setOnClickListener(this);
        option3 = findViewById(R.id.option3);
        option3.setOnClickListener(this);
        option4 = findViewById(R.id.option4);
        option4.setOnClickListener(this);
        gridLayout = findViewById(R.id.favorites_grid);
        constraintLayout = findViewById(R.id.constraintLayout);
        timerTextV = findViewById(R.id.timerTextV);
        scoreTextV = findViewById(R.id.scoreTextV);
        totalScore = findViewById(R.id.totalScore);
        sumProblem = findViewById(R.id.sumProblem);

        animationPartOne();

        countDownTimer = new CountDownTimer(120000+1000,1000) {
            @Override
            public void onTick(long l) {
                int k = (int)l/1000;
                timerTextV.setText("Timer: "+Integer.toString(k)+"s");
            }

            @Override
            public void onFinish() {
                int totalS = Integer.parseInt(totalScore.getText().toString());
                totalS++;
                totalScore.setText(Integer.toString(totalS));
                resultGenerator();

            }
        };

        sumProblemGenerator();
        countDownTimer.start();
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultGenerator();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.option1:
                optionT = Integer.parseInt(option1.getText().toString());
                comparisonFunc(optionT);
                animationPartOne();
                sumProblemGenerator();
                break;

            case R.id.option2:
                optionT = Integer.parseInt(option2.getText().toString());
                comparisonFunc(optionT);
                animationPartOne();

                sumProblemGenerator();
                break;

            case R.id.option3:
                optionT = Integer.parseInt(option3.getText().toString());
                comparisonFunc(optionT);
                animationPartOne();

                sumProblemGenerator();
                break;

            case R.id.option4:
                optionT = Integer.parseInt(option4.getText().toString());
                comparisonFunc(optionT);
                animationPartOne();

                sumProblemGenerator();
                break;
        }
    }


}