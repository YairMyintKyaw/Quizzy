package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends AppCompatActivity {
    int score = QuizPage.score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView passOrFail = findViewById(R.id.PassOrFail);
        TextView congratulation = findViewById(R.id.Congratulation);
        Button goHomeBtn= findViewById(R.id.goHomeBtn);
        TextView point = findViewById(R.id.Point);

        if(score>5){
            passOrFail.setText("Pass");
            congratulation.setText("Congratulatoins");
        }else{
            passOrFail.setText("Fail");
            congratulation.setText("Quiz Ends");
        }

        point.setText(score+"/10");

        goHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizPage.score=0;

                Intent intent = new Intent(Result.this,HomePage.class);
                startActivity(intent);

                MediaPlayer.create(Result.this,R.raw.btn_click_sound).start();
            }
        });



    }
}